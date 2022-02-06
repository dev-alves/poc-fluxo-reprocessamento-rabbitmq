package com.example.pocfluxoreprocessamentorabbitmq.infrastructure.rabbitmq;

import com.example.pocfluxoreprocessamentorabbitmq.domain.model.Pedido;
import com.example.pocfluxoreprocessamentorabbitmq.domain.service.CadastroPedidoHandleErrorSevice;
import com.example.pocfluxoreprocessamentorabbitmq.domain.service.CadastroPedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class CadastroPedidoReprocessListenner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CadastroPedidoReprocessListenner.class);

    private CadastroPedidoService cadastroPedidoService;
    private CadastroPedidoHandleErrorSevice cadastroPedidoHandleErrorSevice;
    private MessageConverter messageConverter;

    public CadastroPedidoReprocessListenner(CadastroPedidoService cadastroPedidoService,
                                            CadastroPedidoHandleErrorSevice cadastroPedidoHandleErrorSevice,
                                            MessageConverter messageConverter) {
        this.cadastroPedidoService = cadastroPedidoService;
        this.cadastroPedidoHandleErrorSevice = cadastroPedidoHandleErrorSevice;
        this.messageConverter = messageConverter;
    }

    @RabbitListener(queues = {"DELAYED-REPROCESS-MESSAGE-ERROR-QUEUE"})
    public void reprocess(Message message) {
        MessageProperties properties = message.getMessageProperties();
        int redelayedCount = properties.getHeader("x-redelayed-count");

        LOGGER.info("reprocess, redelayedCount=" + redelayedCount);

        if (redelayedCount <= LimitRedelivered.LIMIT) {
            try {
                Pedido pedido = (Pedido) messageConverter.fromMessage(message);
                cadastroPedidoService.cadastrar(pedido);

            } catch (NullPointerException e) {
                cadastroPedidoHandleErrorSevice.sentToExchangeNullPointer(message);
            }
        } else {
            LOGGER.info("Limite de retentativas foi alcançado=" + redelayedCount);
        }
    }

}
