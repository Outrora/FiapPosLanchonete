package br.com.lanchonete.core.domain.pedido.Validadores;

import br.com.lanchonete.core.application.Pedido.PedidoRequest;
import br.com.lanchonete.core.domain.exception.ErroValidacao;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class ValidadarProduto implements ValidarPedido {


    @Override
    public void validar(PedidoRequest request) throws ErroValidacao {
        if (request.getProdutos().isEmpty()) {
            throw new ErroValidacao("Produtos Vazio");
        }

        Set<Long> ids = new HashSet<>();
        for (var produto : request.getProdutos()) {
            if (!ids.add(produto.id())) {
                throw new ErroValidacao("ID repetido encontrado: " + produto.id());
            }
        }


    }
}
