package com.tata.accountservice.service;

import com.tata.accountservice.dto.ReportDTO;
import com.tata.accountservice.mapper.ReportMapper;
import com.tata.accountservice.model.ClientReplicaEntity;
import com.tata.accountservice.model.MovementEntity;
import com.tata.accountservice.repository.ClientReplicaRepository;
import com.tata.accountservice.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ClientReplicaRepository clientReplicaRepository;
    private final MovementRepository movementRepository;
    private final ReportMapper reportMapper;

    public List<ReportDTO> generateReport(String dates, Long clientId) {
        String[] parts = dates.split("-");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (parts[0].contains("-")) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }

        LocalDateTime start = LocalDate.parse(parts[0], formatter).atStartOfDay();
        LocalDateTime end = LocalDate.parse(parts[1], formatter).atTime(23, 59, 59);

        ClientReplicaEntity client = clientReplicaRepository.findById(clientId).orElse(null);

        List<MovementEntity> movements = movementRepository.getByClientAndDateRange(clientId, start, end);

        return movements.stream()
                .map(mov -> {
                            ReportDTO reportDto = reportMapper.toReportDTO(mov, mov.getAccount(), client.getName());
                            reportDto.setInitialBalance(mov.getBalance().subtract(mov.getAmount()));
                            return reportDto;
                        }
                )
                .collect(Collectors.toList());
    }
}
