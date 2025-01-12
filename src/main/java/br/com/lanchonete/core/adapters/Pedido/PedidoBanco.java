package br.com.lanchonete.core.adapters.Pedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.core.entities.enums.EstadoPedido;
import br.com.lanchonete.drivers.db.pedido.PedidoDTO;

public interface PedidoBanco {

    UUID inserirPedido(Pedido pedido, Long filaId, List<PedidoRequest.produtoQuantidade> produtos);

    Optional<PedidoDTO> buscarPorId(UUID id);

    void alteraEstadoPedido(UUID id, EstadoPedido estadoPedido);

}
