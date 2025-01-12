package br.com.lanchonete.core.userCases.pedido.Validadores;

import br.com.lanchonete.core.adapters.Pedido.PedidoRequest;
import br.com.lanchonete.core.userCases.exception.ErroValidacao;

public interface ValidarPedido {

    void validar(PedidoRequest request) throws ErroValidacao;
}
