package br.com.lanchonete.core.adapters.Pedido;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import br.com.lanchonete.core.adapters.base.BaseGateway;
import br.com.lanchonete.core.entities.FilaPedidos;
import br.com.lanchonete.core.entities.Pedido;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PedidoGateway extends BaseGateway implements IPedidoGateway {

    @Inject
    PedidoBanco peristencia;

    @Override
    @Transactional
    public UUID salvar(Pedido pedido, FilaPedidos filaAtual, List<PedidoRequest.produtoQuantidade> produtos) {
        LOG.info("Salvando Pedido");
        return peristencia.inserirPedido(pedido, filaAtual.id(), produtos);
    }
}
