package br.com.lanchonete.core.userCases.cozinha;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.lanchonete.core.entities.Pedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FilaUseCase {

    @Inject
    ListaPedidoUseCase listaPedidoUseCase;

    public List<Pedido> pegarFila() {
        var pedidos = listaPedidoUseCase.pegarFilaComPedidos();
        var listaPedidos = pedidos.listaPedidos()
                .stream()
                .filter((pedido) -> pedido.getEstadoPedido().getOrdem() != 0)
                .collect(Collectors.toList());
        listaPedidos.sort(Comparator.comparing((Pedido pedido) -> pedido.getEstadoPedido().getOrdem())
                .thenComparing(Pedido::getDataCriacao, Comparator.nullsFirst(Comparator.naturalOrder())));

        return listaPedidos;
    }

}
