package com.tata.accountservice.model;

import com.tata.accountservice.types.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "account")
public class AccountEntity implements Serializable {

	@Serial
    private static final long serialVersionUID = -1728955713069297090L;

	@Id
	@Column(name = "account_number")
	private String accountNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "account_type")
	private AccountType accountType;

	@Column(name = "initial_balance")
	private BigDecimal initialBalance;

	private Boolean status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private ClientReplicaEntity client;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	private List<MovementEntity> movements;

}