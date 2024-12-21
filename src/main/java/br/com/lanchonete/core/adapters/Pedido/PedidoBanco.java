package br.com.lanchonete.core.adapters.Pedido;

import java.util.List;
import java.util.UUID;

import br.com.lanchonete.core.entities.Pedido;

public interface PedidoBanco {

    UUID inserirPedido(Pedido pedido, Long filaId, List<PedidoRequest.produtoQuantidade> produtos);
}
