package com.tata.accountservice.service;

import com.tata.accountservice.AccountServiceApplication;
import com.tata.accountservice.dto.MovementDTO;
import com.tata.accountservice.exception.NotAllowedException;
import com.tata.accountservice.model.MovementEntity;
import com.tata.accountservice.repository.MovementRepository;
import com.tata.accountservice.types.MovementType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AccountServiceApplication.class)
class MovementServiceTest {
	
	@Autowired
	private MovementService movementService;
	
	@Autowired
	private MovementRepository movementRepository;
	
	/**
	 *Los valores cuando son crédito son positivos, y los débitos son negativos. 
	 *Debe almacenarse el saldo disponible en cada transacción dependiendo del tipo 
	 *de movimiento. (suma o resta)  
	 */
	@Test
	@DirtiesContext
	void test_create_ok() {
		MovementDTO movement = MovementDTO.builder()
				.accountNumber("478758")
				.amount(new BigDecimal(-50))
				.build();
		
		movementService.create(movement);
		
		List<MovementEntity> movements = movementRepository.findAll();
		Optional<MovementEntity> resultMovement = movements.stream()
			.filter(mov -> mov.getAmount().compareTo(BigDecimal.valueOf(-50)) == 0)
			.findFirst();
		assertTrue(resultMovement.isPresent());
		assertEquals(MovementType.DEBIT, resultMovement.get().getMovementType());
	}
	
	/**
	 * Si el saldo es cero, y va a realizar un débito, debe desplegar mensaje 
	 * “Saldo no disponible” 
	 */
	@Test
	@DirtiesContext
	void test_create_greater_than_funds() {
		MovementDTO movement = MovementDTO.builder()
				.accountNumber("496825")
				.amount(new BigDecimal(-600))
				.build();
		
		Exception exception = assertThrows(NotAllowedException.class, () ->
			 movementService.create(movement));
		
		assertEquals(MovementService.INSUFFICIENT_FUNDS, exception.getMessage());
	}
}
