package com.tata.accountservice.controller;

import com.tata.accountservice.dto.ReportDTO;
import com.tata.accountservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReportService reportService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReportDTO> getReport(@RequestParam("fecha") String dateRange, @RequestParam("cliente") Long clientId) {
        return reportService.generateReport(dateRange, clientId);
    }
}
