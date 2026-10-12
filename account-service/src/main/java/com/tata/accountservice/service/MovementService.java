package com.tata.accountservice.service;

import com.tata.accountservice.dto.MovementDTO;
import com.tata.accountservice.exception.InternalException;
import com.tata.accountservice.exception.NotAllowedException;
import com.tata.accountservice.exception.NotFoundException;
import com.tata.accountservice.mapper.MovementMapper;
import com.tata.accountservice.model.AccountEntity;
import com.tata.accountservice.model.MovementEntity;
import com.tata.accountservice.repository.MovementRepository;
import com.tata.accountservice.types.MovementType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("movementService")
@RequiredArgsConstructor
@Log4j2
public class MovementService {

	public static final String SORRY_WE_HAVE_SOME_PROBLEMS = "Sorry we have some problems";
	public static final String ACCOUNT_NUMBER_AND_MOVEMENT_OWNERS =
			"Account number {} and movement id {} has different owners.";
	public static final String MOVEMENT_ID_WAS_NOT_FOUND = "Movement id %d was not found.";
	public static final String THE_AMOUNT_MUST_NOT_BE_ZERO = "The amount must not be zero.";
	public static final String INSUFFICIENT_FUNDS = "Insufficient funds.";

	private final AccountService accountService;
	private final MovementRepository movementRepository;
	private final MovementMapper movementMapper;

	public List<MovementDTO> getMovementsByAccountNumber(final String accountNumber) {
		final List<MovementEntity> movements = movementRepository.getByAccountAccountNumber(accountNumber);
		return movements.stream()
				.map(movementMapper::toDto)
				.collect(Collectors.toList());
	}

	@Transactional
	public void create(final MovementDTO movementDto) {
		final AccountEntity account =
				accountService.getAccountById(movementDto.getAccountNumber());

		validateMovement(account, movementDto);

		final BigDecimal initialBalance = account.getInitialBalance();
		final BigDecimal currentBalance = initialBalance.add(movementDto.getAmount());

		//Create movement
		final MovementEntity movement = MovementEntity.builder()
				.account(account)
				.balance(currentBalance)
				.date(LocalDateTime.now())
				.amount(movementDto.getAmount())
				.movementType(defineMovementType(movementDto.getAmount()))
				.build();
		movementRepository.save(movement);

		// Update account balance
		account.setInitialBalance(currentBalance);
		accountService.save(account);
	}

	private void validateMovement(final AccountEntity account, final MovementDTO movement) {
		if(movement.getAmount().compareTo(BigDecimal.ZERO) == 0) {
			log.error(THE_AMOUNT_MUST_NOT_BE_ZERO);
			throw new NotAllowedException(THE_AMOUNT_MUST_NOT_BE_ZERO);
		}
		if(account.getInitialBalance().add(movement.getAmount()).compareTo(BigDecimal.ZERO) < 0
				&& account.getInitialBalance().compareTo(movement.getAmount().abs()) < 0) {
			log.error(INSUFFICIENT_FUNDS);
			throw new NotAllowedException(INSUFFICIENT_FUNDS);
		}
	}

	private MovementType defineMovementType(final BigDecimal amount) {
		if(amount.compareTo(BigDecimal.ZERO) > 0) {
			return MovementType.CREDIT;
		} else {
			return MovementType.DEBIT;
		}
	}

	private MovementEntity getById(final Long movementId) {
		final Optional<MovementEntity> movement = movementRepository.findById(movementId);
		if(movement.isEmpty()) {
			throw new NotFoundException(String.format(MOVEMENT_ID_WAS_NOT_FOUND, movementId));
		}
		return movement.get();
	}

	public void delete(final Long movementId) {
		final MovementEntity movement = getById(movementId);

		final BigDecimal currentBalance = movement.getBalance().subtract(movement.getAmount());

		final AccountEntity account = accountService.getAccountById(movement.getAccount().getAccountNumber());
		movementRepository.delete(movement);

		// Update account balance
		account.setInitialBalance(currentBalance);
		accountService.save(account);
	}

	public void update(final MovementDTO movementDto) {
		final AccountEntity account = accountService.getAccountById(movementDto.getAccountNumber());
		final MovementEntity movement = getById(movementDto.getMovementId());

		// To prevent the account does not own the movement
		if(!account.getAccountNumber().equals(movement.getAccount().getAccountNumber())) {
			log.error(ACCOUNT_NUMBER_AND_MOVEMENT_OWNERS,
					movementDto.getAccountNumber(), movementDto.getMovementId());
			throw new InternalException(SORRY_WE_HAVE_SOME_PROBLEMS);
		}

		validateMovement(account, movementDto);
		final BigDecimal initialBalance = account.getInitialBalance();
		final BigDecimal previousAmount = movement.getAmount();
		final BigDecimal currentBalance = initialBalance.subtract(previousAmount).add(movementDto.getAmount());

		movement.setAmount(movementDto.getAmount());
		movement.setDate(movementDto.getDate());
		movement.setMovementType(defineMovementType(movementDto.getAmount()));
		movement.setBalance(currentBalance);
		movementRepository.save(movement);

		// Update account balance
		account.setInitialBalance(currentBalance);
		accountService.save(account);
	}
}