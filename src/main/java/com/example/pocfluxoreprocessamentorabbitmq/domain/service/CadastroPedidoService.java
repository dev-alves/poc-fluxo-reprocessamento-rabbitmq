package com.example.pocfluxoreprocessamentorabbitmq.domain.service;

import com.example.pocfluxoreprocessamentorabbitmq.domain.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CadastroPedidoService {

    private final Logger LOGGER = LoggerFactory.getLogger(CadastroPedidoService.class);

    public void cadastrar(Pedido pedido) {
        LOGGER.info("cadastrar, value=" + pedido);
        throw new NullPointerException();
    }

}
