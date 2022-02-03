package com.example.pocfluxoreprocessamentorabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    private final String queueNameDelayedReprocessMessageError = "DELAYED-REPROCESS-MESSAGE-ERROR-QUEUE";

    @Bean
    public Queue reprocessMessageErrorQueue() {
        return QueueBuilder.durable(queueNameDelayedReprocessMessageError)
                .build();
    }

}
