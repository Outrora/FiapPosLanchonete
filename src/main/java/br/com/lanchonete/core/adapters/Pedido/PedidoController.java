package br.com.lanchonete.core.adapters.Pedido;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.UUID;

import br.com.lanchonete.core.adapters.base.BaseController;
import br.com.lanchonete.core.entities.Pagamento;
import br.com.lanchonete.core.userCases.pedido.PedidoUseCase;
import br.com.lanchonete.core.userCases.pedido.StatusPedidoUseCase;

@RequestScoped
public class PedidoController extends BaseController {

    @Inject
    PedidoUseCase pedidoUseCase;

    @Inject
    StatusPedidoUseCase statusPedidoUseCase;

    public Pagamento cadatrarPedido(PedidoRequest request) {
        LOG.info("Cadastrando pedido");
        return pedidoUseCase.cadastrarPedido(request);
    }

    public String verficarStatusPedido(UUID id) {
        LOG.info("Verificando status do pedido");
        return statusPedidoUseCase.buscarStatusPedido(id);
    }

}
