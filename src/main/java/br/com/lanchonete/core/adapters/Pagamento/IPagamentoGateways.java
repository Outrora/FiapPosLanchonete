package br.com.lanchonete.core.adapters.Pagamento;

import br.com.lanchonete.core.entities.Pedido;

public interface IPagamentoGateways {

    String criarPagamento(Pedido pedido);

}
