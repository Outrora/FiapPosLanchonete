package br.com.lanchonete.core.adapters.Pedido;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import br.com.lanchonete.core.adapters.base.BaseGateway;
import br.com.lanchonete.core.entities.FilaPedidos;
import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.entities.enums.EstadoPedido;
import br.com.lanchonete.drivers.AplicacaoMapper;
import br.com.lanchonete.drivers.db.pedido.PedidoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.antlr.v4.runtime.misc.Pair;

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

    @Override
    public Pedido buscarPorId(UUID id) {
        return peristencia.buscarPorId(id)
                .map(this::toPedido)
                .orElseThrow();
    }

    @Override
    public Optional<Pedido> buscarSomentePedido(UUID id) {
        return peristencia.buscarPorId(id).map((pedido) -> {
            return new Pedido(pedido.getDataCriacao(), Optional.of(pedido.getId()), pedido.getEstadoPedido(),
                    pedido.getPreco());
        });
    }

    private Pedido toPedido(PedidoDTO pedidoDTO) {
        List<Pair<Integer, Produto>> produtos = pedidoDTO.getPedidoProdutos()
                .stream()
                .map(produto -> new Pair<>(produto.getQuantidade(),
                        AplicacaoMapper.INSTANCE.toProduto(produto.getProduto())))
                .toList();

        var cliente = Optional.of(AplicacaoMapper.INSTANCE.toCliente(pedidoDTO.getCliente()));

        return new Pedido(pedidoDTO.getDataCriacao(), cliente, produtos,
                pedidoDTO.getEstadoPedido(), pedidoDTO.getPreco());

    }

    @Override
    public void alteraEstadoPedido(UUID id, EstadoPedido estadoPedido) {
        peristencia.alteraEstadoPedido(id, estadoPedido);
    }
}
