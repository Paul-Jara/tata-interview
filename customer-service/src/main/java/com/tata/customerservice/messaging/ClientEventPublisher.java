package com.tata.customerservice.messaging;

import com.tata.customerservice.model.ClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ClientEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishClientEvent(final ClientEntity client, final String action) {
        final Map<String, Object> event = new HashMap<>();
        Long clientId = client.getClientId() != null ? client.getClientId() : client.getPersonId();
        event.put("clientId", clientId);
        event.put("name", client.getName());
        event.put("action", action);
        rabbitTemplate.convertAndSend("clientExchange", "client.routing.key", event);
    }
}
