package com.example.pocfluxoreprocessamentorabbitmq.api;

import com.example.pocfluxoreprocessamentorabbitmq.domain.model.Pedido;
import com.example.pocfluxoreprocessamentorabbitmq.domain.service.CadastroPedidoHandleErrorSevice;
import com.example.pocfluxoreprocessamentorabbitmq.domain.service.CadastroPedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CadastroPedidoService cadastroPedidoService;
    private final CadastroPedidoHandleErrorSevice cadastroPedidoHandleErrorSevice;
    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoController.class);

    public PedidoController(CadastroPedidoService cadastroPedidoService, CadastroPedidoHandleErrorSevice cadastroPedidoHandleErrorSevice) {
        this.cadastroPedidoService = cadastroPedidoService;
        this.cadastroPedidoHandleErrorSevice = cadastroPedidoHandleErrorSevice;
    }

    @PostMapping
    public void obterTodos(@RequestBody Pedido pedido) {
        LOGGER.info("M=obterTodos, B={}", pedido);

        try {
            cadastroPedidoService.cadastrar(pedido);
        } catch (CannotCreateTransactionException e) {
            cadastroPedidoHandleErrorSevice.sendToExchangeDatabaseDown(pedido);
        }
    }

}
