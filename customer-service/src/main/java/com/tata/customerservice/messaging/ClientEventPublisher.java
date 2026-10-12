package com.tata.customerservice.messaging;

import com.tata.customerservice.model.ClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ClientEventPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishClientEvent(final ClientEntity client) {
        final Map<String, Object> event = new HashMap<>();
        event.put("clientId", client.getClientId());
        event.put("name", client.getName());
        rabbitTemplate.convertAndSend("clientExchange", "client.routing.key", event);
    }
}
