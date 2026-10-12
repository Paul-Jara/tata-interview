package com.tata.customerservice.service;

import com.tata.customerservice.dto.ClientDTO;
import com.tata.customerservice.exception.NotAllowedException;
import com.tata.customerservice.exception.NotFoundException;
import com.tata.customerservice.mapper.ClientMapper;
import com.tata.customerservice.model.ClientEntity;
import com.tata.customerservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("clientService")
@RequiredArgsConstructor
@Log4j2
public class ClientService {

	private static final String IDENTIFICATION_DUPLICATED = "This identification %s already exists";
	public static final String CLIENT_WAS_NOT_FOUND = "Client id %d was not found.";
	
	private final ClientRepository clientRepository;
	private final ClientMapper clientMapper;

	public void save(ClientDTO client) {
		client.setStatus(Boolean.TRUE);
		ClientEntity clientEntity = clientMapper.toEntity(client);
		try {
			clientRepository.save(clientEntity);
		} catch (DataIntegrityViolationException e) {
			log.error(String.format(IDENTIFICATION_DUPLICATED, client.getIdentification()));
			log.error(e.getMessage());
			throw new NotAllowedException(
					String.format(IDENTIFICATION_DUPLICATED, client.getIdentification()));
		}
	}

	public ClientEntity getById(Long clientId) {
		Optional<ClientEntity> client = clientRepository.findById(clientId);
		if(client.isEmpty()) {
			log.error(String.format(CLIENT_WAS_NOT_FOUND, clientId));
			throw new NotFoundException(String.format(CLIENT_WAS_NOT_FOUND, clientId));
		}
		return client.get();
	}

	public ClientDTO getClientById(Long clientId) {
		return clientMapper.toDto(getById(clientId));
	}

	public void create(ClientDTO client) {
		client.setStatus(Boolean.TRUE);
		ClientEntity clientEntity = clientMapper.toEntity(client);
		try {
			clientRepository.save(clientEntity);
		} catch (DataIntegrityViolationException e) {
			log.error(String.format(IDENTIFICATION_DUPLICATED, client.getIdentification()));
			log.error(e.getMessage());
			throw new NotAllowedException(
					String.format(IDENTIFICATION_DUPLICATED, client.getIdentification()));
		}
	}

	public void delete(Long id) {
		ClientEntity client = getById(id);
		clientRepository.delete(client);
		
	}

	public void update(ClientDTO clientDto) {
		ClientEntity clientEntity = getById(clientDto.getClientId());
		clientEntity.setAddress(clientDto.getAddress());
		clientEntity.setAge(clientDto.getAge());
		clientEntity.setGender(clientDto.getGender());
		clientEntity.setIdentification(clientDto.getIdentification());
		clientEntity.setName(clientDto.getName());
		clientEntity.setPassword(clientDto.getPassword());
		clientEntity.setPhone(clientDto.getPhone());
		clientEntity.setStatus(clientDto.isStatus());
		clientRepository.save(clientEntity);
		
	}

	public List<ClientDTO> getAll() {
		List<ClientEntity> clientsEntity = clientRepository.findAll();
		return clientsEntity.stream()
				.map(clientMapper::toDto)
				.collect(Collectors.toList());
	}
}