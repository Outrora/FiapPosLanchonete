package br.com.lanchonete.adapters.driver.cozinha;

import br.com.lanchonete.adapters.AplicacaoMapper;
import br.com.lanchonete.core.application.cozinha.FilaDados;
import br.com.lanchonete.core.domain.entities.FilaPedidos;
import br.com.lanchonete.core.domain.entities.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.antlr.v4.runtime.misc.Pair;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@ApplicationScoped
public class FilaRepositoy implements PanacheRepository<FilaPedidoDTO>, FilaDados {

    @Override
    public FilaPedidos criarOuPegarFilaHoje() {
        var filaExite = find("dia", LocalDate.now()).firstResultOptional();

        return filaExite.map(fila ->
                new FilaPedidos(Collections.emptyList(), fila.getDia(), fila.getId())
        ).orElseGet(() -> {
            var novaFila = new FilaPedidoDTO();
            novaFila.setDia(LocalDate.now());
            persist(novaFila);
            return new FilaPedidos(Collections.emptyList(), novaFila.getDia(), novaFila.getId());
        });
    }

    public FilaPedidos pegarFilaComPedidos() {
        var filaExite = find("dia", LocalDate.now()).firstResultOptional();
        if (filaExite.isEmpty()) return criarOuPegarFilaHoje();

        var lista = filaExite
                .get()
                .getListaPedidos()
                .stream()
                .map(pedidoDTO -> {
                    var produtos = pedidoDTO
                            .getPedidoProdutos()
                            .stream()
                            .map(pedidoProduto -> new Pair<>(pedidoProduto.getQuantidade(), AplicacaoMapper.INSTANCE.toProduto(pedidoProduto.getProduto())))
                            .toList();
                    var cliente = AplicacaoMapper.INSTANCE.toCliente(pedidoDTO.getCliente());
                    return new Pedido(pedidoDTO.getDataCriacao(), Optional.of(cliente), produtos, pedidoDTO.getEstadoPedido(), pedidoDTO.getPreco());
                })
                .toList();
        return new FilaPedidos(lista, filaExite.get().getDia(), filaExite.get().getId());
    }


}
