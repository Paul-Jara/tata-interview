package com.tata.accountservice.controller;

import com.tata.accountservice.dto.AccountDTO;
import com.tata.accountservice.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuentas")
public class AccountController {
	
	private final AccountService accountService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AccountDTO> getAll() {
		return accountService.getAll();
	}

	@GetMapping("/{account-number}")
	@ResponseStatus(HttpStatus.OK)
	public AccountDTO getAccountByNumber(@PathVariable("account-number") String accountNumber) {
		return accountService.getAccountDTOByAccountNumber(accountNumber);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid AccountDTO accountDto) {
		accountService.create(accountDto);
	}
	
	@PutMapping("/{account-number}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable(name = "account-number") String accountNumber,
					   @RequestBody @Valid AccountDTO accountDto) {
		accountDto.setAccountNumber(accountNumber);
		accountService.update(accountDto);
	}
	
	@DeleteMapping("/{account-number}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("account-number") String accountNumber) {
		accountService.delete(accountNumber);
	}
}