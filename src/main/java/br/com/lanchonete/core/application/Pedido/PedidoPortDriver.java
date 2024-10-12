package br.com.lanchonete.core.application.Pedido;

import br.com.lanchonete.core.domain.entities.FilaPedidos;
import br.com.lanchonete.core.domain.entities.Pedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PedidoPortDriver {

    @Inject
    PedidoBanco peristencia;


    @Transactional
    public UUID salvar(Pedido pedido, FilaPedidos filaAtual, List<PedidoRequest.produtoQuantidade> produtos) {
        return peristencia.inserirPedido(pedido, filaAtual.id(), produtos);
    }
}
