package br.com.lanchonete.core.userCases.pagamento.validacoes;

import java.util.Optional;

import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.core.userCases.exception.ErroValidacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ValidacaoPedido implements ValidacoesPagamento {

    @Override
    public void validar(Optional<Pedido> pedido, String estadoPagamento) throws ErroValidacao {
        var pedidoValido = pedido.orElseThrow(() -> new ErroValidacao("Pedido não encontrado"));
        if (pedidoValido.getId().isEmpty()) {
            throw new ErroValidacao("Pedido não encontrado");
        }
        if (pedidoValido.getEstadoPedido().getPagamentoRelizado()) {
            throw new ErroValidacao("Pagamento já realizado");
        }
    }

}
