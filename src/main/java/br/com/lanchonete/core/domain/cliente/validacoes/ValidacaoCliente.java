package br.com.lanchonete.core.domain.cliente.validacoes;

import br.com.lanchonete.core.domain.entities.Cliente;
import br.com.lanchonete.core.domain.exception.ErroValidacao;


public interface ValidacaoCliente {

    void validar(Cliente cliente)  throws ErroValidacao;

}
