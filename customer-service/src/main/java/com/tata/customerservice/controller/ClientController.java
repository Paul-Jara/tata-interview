package com.tata.customerservice.controller;

import com.tata.customerservice.dto.ClientDTO;
import com.tata.customerservice.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClientController {
	
	private final ClientService clientService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid ClientDTO clientDto) {
		clientService.create(clientDto);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ClientDTO> getAll() {
		return clientService.getAll();
	}

	@GetMapping("/{client-id}")
	@ResponseStatus(HttpStatus.OK)
	public ClientDTO getClient(@PathVariable("client-id") Long clientId) {
		return clientService.getClientById(clientId);
	}
	
	@PutMapping("/{client-id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable(name = "client-id") Long clientId,
					   @RequestBody @Valid ClientDTO clientDto) {
		clientDto.setClientId(clientId);
		clientService.update(clientDto);
	}
	
	@DeleteMapping("/{client-id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("client-id") Long clientId) {
		clientService.delete(clientId);
	}
}
