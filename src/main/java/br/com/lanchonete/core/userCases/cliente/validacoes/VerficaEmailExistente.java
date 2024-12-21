package br.com.lanchonete.core.userCases.cliente.validacoes;

import br.com.lanchonete.core.adapters.Cliente.ClienteDB;
import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.core.userCases.exception.ErroValidacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VerficaEmailExistente implements ValidacaoCliente {

    @Override
    public void validar(Cliente cliente, ClienteDB banco) throws ErroValidacao {

        var busca = banco.buscarEmail(cliente.getEmail());
        if (busca.isPresent()) {
            throw new ErroValidacao("Email já cadastrado");
        }
    }
}
