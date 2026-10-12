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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ClientReplicaRepository clientReplicaRepository;
    private final MovementRepository movementRepository;
    private final ReportMapper reportMapper;

    public List<ReportDTO> generateReport(LocalDate startDate, LocalDate endDate, Long clientId) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);

        ClientReplicaEntity client = clientReplicaRepository.findById(clientId).orElse(null);

        List<MovementEntity> movements = movementRepository.getByClientAndDateRange(clientId, start, end);

        return movements.stream()
                .map(mov -> reportMapper.toReportDTO(mov, mov.getAccount(), client.getName()))
                .collect(Collectors.toList());
    }
}
