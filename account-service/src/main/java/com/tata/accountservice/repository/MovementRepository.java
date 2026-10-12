package com.tata.accountservice.repository;

import com.tata.accountservice.model.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementRepository extends JpaRepository<MovementEntity, Long>{
	@Query("select m "
			+ "from MovementEntity m "
			+ "where m.account.client.clientId = :clientId "
			+ "and m.date between :from and :to")
	List<MovementEntity> getByClientAndDateRange(@Param("clientId") final Long clientId,
			@Param("from") LocalDateTime from, 
			@Param("to") LocalDateTime to);

	List<MovementEntity> getByAccountAccountNumber(final String accountNumber);

}
