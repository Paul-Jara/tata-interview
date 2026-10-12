package com.tata.customerservice.mapper;

import com.tata.customerservice.dto.ClientDTO;
import com.tata.customerservice.model.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	
	ClientEntity toEntity(ClientDTO source);
	
	ClientDTO toDto(ClientEntity source);
}
