package com.tata.accountservice.messaging;

import com.tata.accountservice.model.ClientReplicaEntity;
import com.tata.accountservice.repository.ClientReplicaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class ClientEventSubscriber {

    private final ClientReplicaRepository clientReplicaRepository;

    @RabbitListener(queues = "clientQueue")
    public void receiveMessage(final Map<String, Object> message) {
        log.info("Received client event message: {}", message);
        Object idObj = message.get("clientId");
        if (idObj == null) {
            log.warn("Received message without clientId: {}", message);
            return;
        }

        final Long clientId;
        if (idObj instanceof Number) {
            clientId = ((Number) idObj).longValue();
        } else {
            clientId = Long.parseLong(idObj.toString());
        }

        final String action = (String) message.get("action");
        if (action.equals("DELETE")) {
            clientReplicaRepository.deleteById(clientId);
            log.info("Client replica deleted successfully: clientId={}", clientId);
            return;
        }

        final String name = (String) message.get("name");
        if (clientId > 0 && name != null) {
            final ClientReplicaEntity client = ClientReplicaEntity.builder()
                    .clientId(clientId)
                    .name(name)
                    .build();
            clientReplicaRepository.save(client);
            log.info("Client replica saved successfully: clientId={}, name={}", clientId, name);
        }
    }
}
