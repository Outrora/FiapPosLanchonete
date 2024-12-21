package br.com.lanchonete.core.userCases.cliente.validacoes;

import br.com.lanchonete.core.adapters.Cliente.ClienteDB;
import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.core.userCases.exception.ErroValidacao;

public interface ValidacaoCliente {

    void validar(Cliente cliente, ClienteDB banco) throws ErroValidacao;

}
