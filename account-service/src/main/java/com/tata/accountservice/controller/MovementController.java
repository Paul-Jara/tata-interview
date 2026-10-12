package com.tata.accountservice.controller;

import com.tata.accountservice.dto.MovementDTO;
import com.tata.accountservice.service.MovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movimientos")
public class MovementController {

	private final MovementService movementService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid @Validated final MovementDTO movementDto) {
		movementService.create(movementDto);
	}

	@GetMapping("/account/{account-number}")
	@ResponseStatus(HttpStatus.OK)
	public List<MovementDTO> getAllByAccountNumber(@PathVariable("account-number") final String accountNumber) {
		return movementService.getMovementsByAccountNumber(accountNumber);
	}

	@PutMapping("{movement-id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable(name = "movement-id") final Long movementId, @RequestBody @Valid final MovementDTO movementDto) {
		movementDto.setMovementId(movementId);
		movementService.update(movementDto);
	}

	@DeleteMapping("/{movement-id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("movement-id") final Long movementId) {
		movementService.delete(movementId);
	}
}
