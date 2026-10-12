package com.tata.accountservice.repository;

import com.tata.accountservice.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, String>{
}
