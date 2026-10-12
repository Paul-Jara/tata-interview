package com.tata.customerservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "client_id")
public class ClientEntity extends PersonEntity implements Serializable {

	private static final long serialVersionUID = 5043735760406023463L;

	@Column(name = "client_id", insertable = false, updatable = false)
	private Long clientId;

	private String password;

	private Boolean status;
}
