package com.example.pocfluxoreprocessamentorabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ExchangeConfig {

    @Bean
    public CustomExchange delayedReprocessMessageErrorExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        args.put("durable", true);

        return new CustomExchange(ExchangeEnum.DELAYED_REPROCESS_MESSAGE_ERROR.getDescricao(), "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingDelayedReprocessMessageErrorExchange(Queue queue) {
        return BindingBuilder.bind(queue)
                .to(delayedReprocessMessageErrorExchange())
                .with(RoutingKeyEnum.DELAYED_REPROCESS_MESSAGE_ERROR.getDescricao())
                .noargs();
    }

}

