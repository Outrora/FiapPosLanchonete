package br.com.lanchonete.core.adapters.Pedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.lanchonete.core.entities.FilaPedidos;
import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.core.entities.enums.EstadoPedido;

public interface IPedidoGateway {

    public UUID salvar(Pedido pedido, FilaPedidos filaAtual, List<PedidoRequest.produtoQuantidade> produtos);

    Pedido buscarPorId(UUID id);

    Optional<Pedido> buscarSomentePedido(UUID id);

    void alteraEstadoPedido(UUID id, EstadoPedido estadoPedido);

}
