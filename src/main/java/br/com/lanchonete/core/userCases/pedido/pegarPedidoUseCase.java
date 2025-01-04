package br.com.lanchonete.core.userCases.pedido;

import java.util.UUID;

import br.com.lanchonete.core.adapters.Pedido.IPedidoGateway;

import br.com.lanchonete.core.entities.Pedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class pegarPedidoUseCase {

    @Inject
    private IPedidoGateway pedidoGateway;

    public Pedido pegarPedido(UUID id) {
        return pedidoGateway.buscarPorId(id);
    }

}
