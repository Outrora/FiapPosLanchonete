package br.com.lanchonete.core.adapters.cozinha;

import java.util.ArrayList;
import java.util.Optional;

import org.antlr.v4.runtime.misc.Pair;

import br.com.lanchonete.core.entities.FilaPedidos;
import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.drivers.AplicacaoMapper;
import br.com.lanchonete.drivers.db.cozinha.FilaPedidoDTO;

public class FilaPedidoMapper {

    public static FilaPedidos toFilaPedidos(FilaPedidoDTO filaPedidos) {
        var lista = Optional.ofNullable(filaPedidos.getListaPedidos()).orElseGet(ArrayList::new);
        var lista_retorno = lista.stream()
                .map(pedidoDTO -> {
                    var produtos = pedidoDTO
                            .getPedidoProdutos()
                            .stream()
                            .map(pedidoProduto -> new Pair<>(pedidoProduto.getQuantidade(),
                                    AplicacaoMapper.INSTANCE.toProduto(
                                            pedidoProduto.getProduto())))
                            .toList();
                    var cliente = Optional.ofNullable(
                            AplicacaoMapper.INSTANCE.toCliente(pedidoDTO.getCliente()));
                    return new Pedido(pedidoDTO.getDataCriacao(), cliente, produtos,
                            pedidoDTO.getEstadoPedido(),
                            pedidoDTO.getPreco(), pedidoDTO.getId());
                })
                .toList();

        return new FilaPedidos(lista_retorno, filaPedidos.getDia(), filaPedidos.getId());
    }

}
