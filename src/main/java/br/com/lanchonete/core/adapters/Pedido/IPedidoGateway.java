package br.com.lanchonete.core.adapters.Pedido;

import java.util.List;
import java.util.UUID;

import br.com.lanchonete.core.entities.FilaPedidos;
import br.com.lanchonete.core.entities.Pedido;

public interface IPedidoGateway {

    public UUID salvar(Pedido pedido, FilaPedidos filaAtual, List<PedidoRequest.produtoQuantidade> produtos);

}
