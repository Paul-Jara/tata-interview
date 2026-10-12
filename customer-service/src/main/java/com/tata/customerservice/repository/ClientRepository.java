package com.tata.customerservice.repository;

import com.tata.customerservice.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
    Optional<ClientEntity> findByClientId(final Long clientId);
}
