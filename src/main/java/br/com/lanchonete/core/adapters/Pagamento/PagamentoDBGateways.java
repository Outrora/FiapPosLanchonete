package br.com.lanchonete.core.adapters.Pagamento;

import br.com.lanchonete.core.adapters.Pedido.IPedidoGateway;
import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.core.entities.enums.EstadoPedido;
import br.com.lanchonete.core.userCases.exception.ResultadoVazioErro;

import jakarta.inject.Inject;

public class PagamentoDBGateways implements IPagamentoGateways {

    @Inject
    IPedidoGateway gateway;

    public String criarPagamento(Pedido pedido) {
        System.out.println("Criando pagamento para o pedido: " + pedido);
        var id = pedido.getId().orElseThrow(() -> new ResultadoVazioErro("Pedido sem id"));
        gateway.alteraEstadoPedido(id, EstadoPedido.PAGAMENTO_APROVADO);
        return "Pagamento aprovado";
    }

}
