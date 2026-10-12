package com.tata.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tata.accountservice.types.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
	@NotNull(message = "Account number is required")
	@Size(min = 6, max = 6, message = "Account number must have 6 characters")
	private String accountNumber;

	@NotNull(message = "Account type is required")
	private AccountType accountType;

	@NotNull(message = "Initial balance is required")
	private BigDecimal initialBalance;

	private Boolean status;

	private Long clientId;
}
