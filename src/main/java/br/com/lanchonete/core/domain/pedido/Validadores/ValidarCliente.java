package br.com.lanchonete.core.domain.pedido.Validadores;

import br.com.lanchonete.core.application.Pedido.PedidoRequest;
import br.com.lanchonete.core.domain.exception.ErroValidacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ValidarCliente implements ValidarPedido {


    @Override
    public void validar(PedidoRequest request) throws ErroValidacao {
        if (request.getId_cliente() <= 0) throw new ErroValidacao("Sem Id Cliente");
    }
}
