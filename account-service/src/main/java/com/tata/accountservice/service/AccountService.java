package com.tata.accountservice.service;

import com.tata.accountservice.dto.AccountDTO;
import com.tata.accountservice.model.AccountEntity;
import com.tata.accountservice.exception.NotAllowedException;
import com.tata.accountservice.exception.NotFoundException;
import com.tata.accountservice.mapper.AccountMapper;
import com.tata.accountservice.model.ClientReplicaEntity;
import com.tata.accountservice.repository.AccountRepository;
import com.tata.accountservice.repository.ClientReplicaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("accountService")
@RequiredArgsConstructor
@Log4j2
public class AccountService implements CrudService<AccountDTO, String> {

	private static final String ACCOUNT_NUMBER_DUPLICATED = "This account number %s already exists";
	public static final String ACCOUNT_NUMBER_NOT_FOUND = "Account number %d, was not found.";

	private final AccountRepository accountRepository;
	private final ClientReplicaRepository clientReplicaRepository;
	private final AccountMapper accountMapper;

	public AccountEntity getAccountById(final String accountNumber) {
		final Optional<AccountEntity> account = accountRepository.findById(accountNumber);
		if (account.isEmpty()) {
			log.error(ACCOUNT_NUMBER_NOT_FOUND, accountNumber);
			throw new NotFoundException(String.format(ACCOUNT_NUMBER_NOT_FOUND, accountNumber));
		}
		return account.get();
	}

	public AccountDTO getAccountDTOByAccountNumber(final String accountNumber) {
		return accountMapper.toDto(getAccountById(accountNumber));
	}
	
	public void save(final AccountEntity account) {
		accountRepository.save(account);
	}
	
	public void create(final AccountDTO accountDto) {
		final ClientReplicaEntity client = clientReplicaRepository.getById(accountDto.getClientId());
		final AccountEntity accountEntity = accountMapper.toEntity(accountDto);
		accountEntity.setClient(client);
		try {
			accountRepository.save(accountEntity);
		} catch (final DataIntegrityViolationException e) {
			log.error(String.format(ACCOUNT_NUMBER_DUPLICATED, accountDto.getAccountNumber()));
			log.error(e.getMessage());
			throw new NotAllowedException(
					String.format(ACCOUNT_NUMBER_DUPLICATED, accountDto.getAccountNumber()));
		}
	}

	public void delete(final String accountNumber) {
		final AccountEntity account = getAccountById(accountNumber);
		accountRepository.delete(account);
	}

	public void update(final AccountDTO accountDto) {
		final AccountEntity accountEntity = getAccountById(accountDto.getAccountNumber());
		accountEntity.setAccountNumber(accountDto.getAccountNumber());
		accountEntity.setAccountType(accountDto.getAccountType());
		// To edit the balance must not be allowed
		accountEntity.setInitialBalance(accountDto.getInitialBalance());
		accountEntity.setStatus(accountDto.getStatus());
		accountRepository.save(accountEntity);
	}

	public List<AccountDTO> getAll() {
		final List<AccountEntity> accountsEntity = accountRepository.findAll();
		return accountsEntity.stream()
				.map(accountMapper::toDto)
				.collect(Collectors.toList());
	}
}
