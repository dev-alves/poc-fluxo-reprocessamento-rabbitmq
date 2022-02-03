package com.example.pocfluxoreprocessamentorabbitmq.infrastructure.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PedidoListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoListener.class);

    @RabbitListener(queues = {"DELAYED-REPROCESS-MESSAGE-ERROR-QUEUE"})
    public void consumer(Message message) {
        LOGGER.info("Recebi a mensagem: %s" + message.toString());
    }
}
