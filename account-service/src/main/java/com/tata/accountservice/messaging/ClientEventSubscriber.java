package com.tata.accountservice.messaging;
import com.tata.accountservice.model.ClientReplicaEntity;
import com.tata.accountservice.repository.ClientReplicaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ClientEventSubscriber {

    final private ClientReplicaRepository clientReplicaRepository;

    @RabbitListener(queues = "clientQueue")
    public void receiveMessage(final Map<String, Object> message) {
        final Long clientId = Long.parseLong((String) message.get("clientId"));
        final String name = (String) message.get("name");
        if (clientId > 0 && name != null) {
            final ClientReplicaEntity client = ClientReplicaEntity.builder().clientId(clientId).name(name).build();
            clientReplicaRepository.save(client);
        }
    }
}
