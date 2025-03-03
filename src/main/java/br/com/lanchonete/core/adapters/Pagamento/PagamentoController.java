package br.com.lanchonete.core.adapters.Pagamento;

import br.com.lanchonete.core.adapters.Pedido.IPedidoGateway;
import br.com.lanchonete.core.adapters.base.BaseController;
import br.com.lanchonete.core.userCases.pagamento.AlterarEstadoPagamentoPedidoUseCase;
import br.com.lanchonete.core.userCases.pagamento.CriarPedidoPagamentoUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PagamentoController extends BaseController {

    @Inject
    AlterarEstadoPagamentoPedidoUseCase useCase;

    @Inject
    CriarPedidoPagamentoUseCase criarPedidoPagamentoUseCase;

    @Inject
    IPedidoGateway pedidoGateway;

    public void processarWebhookPagamento(PagamentoWebHookResquest request) {
        LOG.info("Processando webhook de pagamento");
        var pedido = pedidoGateway.buscarSomentePedido(request.getPedidoId());

        useCase.alterarEstadoPagamentoPedido(pedido, request.getStatusPagamento());
    }

}
