package com.tata.accountservice.mapper;

import com.tata.accountservice.dto.AccountDTO;
import com.tata.accountservice.model.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	
	AccountEntity toEntity(final AccountDTO source);
	
	AccountDTO toDto(final AccountEntity source);
}
