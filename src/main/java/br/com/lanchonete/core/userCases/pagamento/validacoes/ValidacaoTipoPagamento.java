package br.com.lanchonete.core.userCases.pagamento.validacoes;

import java.util.List;
import java.util.Optional;

import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.core.userCases.exception.ErroValidacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ValidacaoTipoPagamento implements ValidacoesPagamento {

    private List<String> status = List.of("PAGO", "NAO_PAGO");

    @Override
    public void validar(Optional<Pedido> pedido, String estadoPagamento) throws ErroValidacao {
        if (status.contains(estadoPagamento.trim().toUpperCase())) {
            return;
        }
        throw new ErroValidacao("Estado do Pagamento inválido");
    }

}
