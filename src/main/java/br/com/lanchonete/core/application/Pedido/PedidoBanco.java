package br.com.lanchonete.core.application.Pedido;

import br.com.lanchonete.core.domain.entities.Pedido;

import java.util.List;
import java.util.UUID;

public interface PedidoBanco {

    UUID inserirPedido(Pedido pedido, Long filaId, List<PedidoRequest.produtoQuantidade> produtos);
}
