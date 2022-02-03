package com.example.pocfluxoreprocessamentorabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ExchangeConfig {

    private final String exchangeNameReprocessMessageError = "DELAYED-REPROCESS-MESSAGE-ERROR-EXCHANGE";

    @Bean
    public CustomExchange delayedReprocessMessageErrorExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        args.put("durable", true);

        return new CustomExchange(exchangeNameReprocessMessageError, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingDelayedReprocessMessageErrorExchange(Queue queue) {
        return BindingBuilder.bind(queue)
                .to(delayedReprocessMessageErrorExchange())
                .with("DELAYED-REPROCESS-MESSAGE-ERROR-QUEUE")
                .noargs();
    }

}

