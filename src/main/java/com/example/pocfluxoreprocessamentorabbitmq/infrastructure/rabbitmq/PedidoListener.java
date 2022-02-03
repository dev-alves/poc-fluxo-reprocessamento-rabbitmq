package com.example.pocfluxoreprocessamentorabbitmq.infrastructure.rabbitmq;

import com.example.pocfluxoreprocessamentorabbitmq.domain.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class PedidoListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoListener.class);
    private MessageConverter messageConverter;

    public PedidoListener(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @RabbitListener(queues = {"DELAYED-REPROCESS-MESSAGE-ERROR-QUEUE"})
    public void consumer(Message message) {
        Pedido pedido = (Pedido) messageConverter.fromMessage(message);

        LOGGER.info("Recebi a mensagem: %s" + message.toString());
        LOGGER.info("Dados da mensagem: " + pedido);
    }
}
