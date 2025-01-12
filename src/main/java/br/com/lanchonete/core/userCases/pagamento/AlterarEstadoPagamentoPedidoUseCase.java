package br.com.lanchonete.core.userCases.pagamento;

import java.util.List;
import java.util.Optional;

import br.com.lanchonete.core.adapters.Pedido.IPedidoGateway;
import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.core.entities.enums.EstadoPedido;
import br.com.lanchonete.core.userCases.pagamento.validacoes.ValidacoesPagamento;
import io.quarkus.arc.All;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AlterarEstadoPagamentoPedidoUseCase {

    @Inject
    IPedidoGateway pedidoGateway;

    @Inject
    @All
    List<ValidacoesPagamento> validacoesPagamento;

    public void alterarEstadoPagamentoPedido(Optional<Pedido> pedido, String estadoPagamento) {
        validacoesPagamento.forEach(validacao -> validacao.validar(pedido, estadoPagamento));

        var pagamento = EstadoPedido.PAGAMENTO_RECUSADO;
        if (estadoPagamento.toUpperCase().trim().equals("PAGO")) {
            pagamento = EstadoPedido.PAGAMENTO_APROVADO;

        }

        pedidoGateway.alteraEstadoPedido(pedido.get().getId().get(), pagamento);
    }
};
