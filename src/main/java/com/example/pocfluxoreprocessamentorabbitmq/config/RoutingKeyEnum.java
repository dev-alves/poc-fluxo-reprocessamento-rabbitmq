package com.example.pocfluxoreprocessamentorabbitmq.config;

import lombok.Getter;

@Getter
public enum RoutingKeyEnum {

    DELAYED_REPROCESS_MESSAGE_ERROR("DELAYED-REPROCESS-MESSAGE-ERROR-QUEUE");

    private String descricao;

    RoutingKeyEnum(String descricao) {
        this.descricao = descricao;
    }
}
