package br.com.lanchonete.core.application.Pedido;

import br.com.lanchonete.core.domain.pedido.PedidoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@RequestScoped
public class PedidoPortDriven {

    @Inject
    PedidoService pedidoService;


    public UUID cadatrarPedido(PedidoRequest request) {
        return pedidoService.cadastrarPedido(request);
    }
}
