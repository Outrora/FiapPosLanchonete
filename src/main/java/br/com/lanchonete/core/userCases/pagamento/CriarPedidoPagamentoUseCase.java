package br.com.lanchonete.core.userCases.pagamento;

import java.util.UUID;

import br.com.lanchonete.core.adapters.Pagamento.IPagamentoGateways;
import br.com.lanchonete.core.entities.Pagamento;
import br.com.lanchonete.core.userCases.pedido.pegarPedidoUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CriarPedidoPagamentoUseCase {

    @Inject
    IPagamentoGateways pagamentoGateways;

    @Inject
    pegarPedidoUseCase pegarPedidoUseCase;

    public Pagamento criarPagamento(UUID id) {
        var pedido = pegarPedidoUseCase.pegarPedido(id);
        System.out.println(pedido);
        var formaPagamento = pagamentoGateways.criarPagamento(pedido);
        return new Pagamento(id, formaPagamento);
    }

}
