package br.com.lanchonete.core.application.Pedido;

import br.com.lanchonete.core.domain.entities.FilaPedidos;
import br.com.lanchonete.core.domain.entities.Pedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PedidoPortDriver {

    private static final Logger log = LoggerFactory.getLogger(PedidoPortDriver.class);
    @Inject
    PedidoBanco peristencia;


    @Transactional
    public UUID salvar(Pedido pedido, FilaPedidos filaAtual, List<PedidoRequest.produtoQuantidade> produtos) {
        log.info("Salvando Pedido");
        return peristencia.inserirPedido(pedido, filaAtual.id(), produtos);
    }
}
