package br.com.lanchonete.core.userCases.pagamento.validacoes;

import java.util.Optional;

import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.core.userCases.exception.ErroValidacao;

public interface ValidacoesPagamento {

    void validar(Optional<Pedido> pedido, String estadoPagamento) throws ErroValidacao;

}
