package com.example.pocfluxoreprocessamentorabbitmq.config;

import lombok.Getter;

@Getter
public enum ExchangeEnum {

    DELAYED_REPROCESS_MESSAGE_ERROR("DELAYED-REPROCESS-MESSAGE-ERROR-EXCHANGE");

    private String descricao;

    ExchangeEnum(String descricao) {
        this.descricao = descricao;
    }
}
