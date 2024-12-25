package br.com.lanchonete.core.userCases.pedido;

import java.util.UUID;

import br.com.lanchonete.core.adapters.Pedido.PedidoGateway;
import br.com.lanchonete.core.userCases.exception.ResultadoVazioErro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StatusPedidoUseCase {
    @Inject
    private PedidoGateway pedidoGateway;

    public String buscarStatusPedido(UUID id) {
        var pedido = pedidoGateway.buscarSomentePedido(id)
                .orElseThrow(() -> new ResultadoVazioErro("Pedido não encontrado"));
        return pedido.getEstadoPedido().getEstadoPagamento();
    }
}
