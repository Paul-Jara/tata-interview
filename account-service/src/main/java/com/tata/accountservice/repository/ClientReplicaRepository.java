package com.tata.accountservice.repository;

import com.tata.accountservice.model.ClientReplicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientReplicaRepository extends JpaRepository<ClientReplicaEntity, Long> {
}
