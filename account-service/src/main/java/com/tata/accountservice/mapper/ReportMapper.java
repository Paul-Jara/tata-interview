package com.tata.accountservice.mapper;

import com.tata.accountservice.dto.ReportDTO;
import com.tata.accountservice.model.AccountEntity;
import com.tata.accountservice.model.MovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    @Mapping(source = "movement.date", target = "date", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "clientName", target = "client")
    @Mapping(source = "account.accountNumber", target = "accountNumber")
    @Mapping(source = "account.accountType", target = "type")
    @Mapping(source = "movement.balance", target = "initialBalance")
    @Mapping(source = "account.status", target = "status")
    @Mapping(source = "movement.amount", target = "movement")
    @Mapping(source = "movement.balance", target = "currentBalance")
    ReportDTO toReportDTO(MovementEntity movement, AccountEntity account, String clientName);
}
