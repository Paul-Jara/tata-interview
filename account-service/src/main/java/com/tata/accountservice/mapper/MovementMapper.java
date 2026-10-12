package com.tata.accountservice.mapper;

import com.tata.accountservice.dto.MovementDTO;
import com.tata.accountservice.model.MovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MovementMapper {

	MovementEntity toEntity(final MovementDTO source);

	@Mappings({ 
		@Mapping(source = "account.accountNumber", target = "accountNumber"),
		@Mapping(source = "account.initialBalance", target = "currentBalance")
	})
	MovementDTO toDto(final MovementEntity source);
}
