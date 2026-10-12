package com.tata.accountservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "client_replica")
public class ClientReplicaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6154075191578020077L;

	@Id
	@Column(name = "client_id", unique = true)
	private Long clientId;

	private String name;
}
