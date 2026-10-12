package com.tata.accountservice.model;

import com.tata.accountservice.types.MovementType;
import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "movement")
public class MovementEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = -2514121580137280621L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movement_id")
	private Long movementId;

	private LocalDateTime date;
	
	@Enumerated(EnumType.STRING)
	private MovementType movementType;
	
	private BigDecimal amount;

	private BigDecimal balance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_number", referencedColumnName = "account_number")
	private AccountEntity account;
}
