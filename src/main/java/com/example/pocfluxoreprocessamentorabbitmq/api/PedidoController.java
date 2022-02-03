package com.example.pocfluxoreprocessamentorabbitmq.api;

import com.example.pocfluxoreprocessamentorabbitmq.domain.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoController.class);

    public PedidoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public void obterTodos() {
        Pedido pedido = new Pedido();
        pedido.setNome("Carretilha Shimano");

        LOGGER.info("Enviando mensagem: %s" + pedido);
        rabbitTemplate.convertAndSend("DELAYED-REPROCESS-MESSAGE-ERROR-EXCHANGE", "DELAYED-REPROCESS-MESSAGE-ERROR-QUEUE", pedido, message -> {
            message.getMessageProperties().setHeader("x-delay", 60000);
            return message;
        });
    }
}
