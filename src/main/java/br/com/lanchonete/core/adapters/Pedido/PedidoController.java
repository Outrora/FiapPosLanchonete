package br.com.lanchonete.core.adapters.Pedido;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.UUID;

import br.com.lanchonete.core.userCases.pedido.PedidoUseCase;

@RequestScoped
public class PedidoController {

    @Inject
    PedidoUseCase pedidoUseCase;

    public UUID cadatrarPedido(PedidoRequest request) {
        return pedidoUseCase.cadastrarPedido(request);
    }
}
