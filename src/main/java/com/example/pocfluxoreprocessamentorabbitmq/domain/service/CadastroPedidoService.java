package com.example.pocfluxoreprocessamentorabbitmq.domain.service;

import com.example.pocfluxoreprocessamentorabbitmq.domain.model.Pedido;
import com.example.pocfluxoreprocessamentorabbitmq.domain.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CadastroPedidoService {

    private final Logger LOGGER = LoggerFactory.getLogger(CadastroPedidoService.class);
    private final PedidoRepository pedidoRepository;

    public CadastroPedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void cadastrar(Pedido pedido) {
        LOGGER.info("cadastrar, value={}", pedido);
        pedidoRepository.save(pedido);
    }

}
