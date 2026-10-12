package com.tata.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tata.accountservice.types.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovementDTO {

	private Long movementId;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDateTime date;

	private MovementType movementType;
	private BigDecimal amount;
	private BigDecimal balance;
	private String name;
	private BigDecimal currentBalance;
	private String accountNumber;

}
