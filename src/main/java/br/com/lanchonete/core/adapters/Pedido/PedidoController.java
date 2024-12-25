package br.com.lanchonete.core.adapters.Pedido;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.UUID;

import br.com.lanchonete.core.userCases.pedido.PedidoUseCase;
import br.com.lanchonete.core.userCases.pedido.StatusPedidoUseCase;

@RequestScoped
public class PedidoController {

    @Inject
    PedidoUseCase pedidoUseCase;

    @Inject
    StatusPedidoUseCase statusPedidoUseCase;

    public UUID cadatrarPedido(PedidoRequest request) {
        return pedidoUseCase.cadastrarPedido(request);
    }

    public String verficarStatusPedido(UUID id) {
        return statusPedidoUseCase.buscarStatusPedido(id);
    }
}
