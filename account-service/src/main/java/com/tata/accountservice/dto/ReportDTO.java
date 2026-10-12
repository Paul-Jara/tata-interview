package com.tata.accountservice.dto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ReportDTO {
    private String date;
    private String client;
    private String accountNumber;
    private String type;
    private BigDecimal initialBalance;
    private Boolean status;
    private BigDecimal movement;
    private BigDecimal availableBalance;
}
