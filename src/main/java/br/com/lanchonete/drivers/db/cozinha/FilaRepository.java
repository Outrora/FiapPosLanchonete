package br.com.lanchonete.drivers.db.cozinha;

import br.com.lanchonete.core.adapters.cozinha.FilaDadosDB;
import br.com.lanchonete.core.entities.FilaPedidos;
import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.drivers.AplicacaoMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@ApplicationScoped
public class FilaRepository implements PanacheRepository<FilaPedidoDTO>, FilaDadosDB {

    @Override
    @Transactional
    public FilaPedidos criarOuPegarFilaHoje() {
        var filaExite = find("dia", LocalDate.now()).firstResultOptional();

        return filaExite.map(fila -> new FilaPedidos(Collections.emptyList(), fila.getDia(), fila.getId()))
                .orElseGet(() -> {
                    var novaFila = new FilaPedidoDTO();
                    novaFila.setDia(LocalDate.now());
                    persist(novaFila);
                    return new FilaPedidos(Collections.emptyList(), novaFila.getDia(), novaFila.getId());
                });
    }

    @Transactional
    public FilaPedidos pegarFilaComPedidos() {
        var filaExite = find("dia", LocalDate.now()).firstResultOptional();
        if (filaExite.isEmpty())
            return criarOuPegarFilaHoje();

        var fila = filaExite.orElseThrow();
        var lista = Optional.ofNullable(fila.getListaPedidos()).orElseGet(ArrayList::new);
        var lista_retorno = lista.stream()
                .map(pedidoDTO -> {
                    var produtos = pedidoDTO
                            .getPedidoProdutos()
                            .stream()
                            .map(pedidoProduto -> new Pair<>(pedidoProduto.getQuantidade(),
                                    AplicacaoMapper.INSTANCE.toProduto(pedidoProduto.getProduto())))
                            .toList();
                    var cliente = Optional.ofNullable(AplicacaoMapper.INSTANCE.toCliente(pedidoDTO.getCliente()));
                    return new Pedido(pedidoDTO.getDataCriacao(), cliente, produtos, pedidoDTO.getEstadoPedido(),
                            pedidoDTO.getPreco());
                })
                .toList();

        return new FilaPedidos(lista_retorno, fila.getDia(), fila.getId());
    }

}
