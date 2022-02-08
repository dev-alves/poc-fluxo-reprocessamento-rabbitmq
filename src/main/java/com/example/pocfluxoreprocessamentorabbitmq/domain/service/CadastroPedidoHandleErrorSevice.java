package com.example.pocfluxoreprocessamentorabbitmq.domain.service;

import com.example.pocfluxoreprocessamentorabbitmq.config.ExchangeEnum;
import com.example.pocfluxoreprocessamentorabbitmq.config.RoutingKeyEnum;
import com.example.pocfluxoreprocessamentorabbitmq.domain.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class CadastroPedidoHandleErrorSevice {

    private final static int START = 0;
    private final static int DELAYED_TIME = 6000;
    private static final Logger LOGGER = LoggerFactory.getLogger(CadastroPedidoHandleErrorSevice.class);

    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter messageConverter;

    public CadastroPedidoHandleErrorSevice(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
    }

    public void sendToExchangeDatabaseDown(Pedido pedido) {
        rabbitTemplate.convertAndSend(ExchangeEnum.DELAYED_REPROCESS_MESSAGE_ERROR.getDescricao(), RoutingKeyEnum.DELAYED_REPROCESS_MESSAGE_ERROR.getDescricao(), pedido, message -> {
            message.getMessageProperties().setHeader("x-delay", DELAYED_TIME);
            message.getMessageProperties().setHeader("x-redelayed-count", START);
            return message;
        });
    }

    public void sendToExchangeDatabaseDown(Message message) {
        MessageProperties properties = message.getMessageProperties();

        Pedido pedido = (Pedido) messageConverter.fromMessage(message);
        int redelayedCountValue = properties.getHeader("x-redelayed-count");

        final int redelayedCount =  ++redelayedCountValue;

        LOGGER.info("sendToExchangeDatabaseDown, redelayedCount={}", redelayedCount);

        rabbitTemplate.convertAndSend(ExchangeEnum.DELAYED_REPROCESS_MESSAGE_ERROR.getDescricao(), RoutingKeyEnum.DELAYED_REPROCESS_MESSAGE_ERROR.getDescricao(), pedido, newMessage -> {
            newMessage.getMessageProperties().setHeader("x-delay", DELAYED_TIME);
            newMessage.getMessageProperties().setHeader("x-redelayed-count", redelayedCount);
            return newMessage;
        });
    }

}
