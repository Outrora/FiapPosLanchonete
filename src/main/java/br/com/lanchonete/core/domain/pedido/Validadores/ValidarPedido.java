package br.com.lanchonete.core.domain.pedido.Validadores;

import br.com.lanchonete.core.application.Pedido.PedidoRequest;
import br.com.lanchonete.core.domain.exception.ErroValidacao;

public interface ValidarPedido {

    void validar(PedidoRequest request) throws ErroValidacao;
}
