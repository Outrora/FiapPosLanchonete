package br.com.lanchonete.core.userCases.cliente.validacoes;

import br.com.lanchonete.core.adapters.Cliente.ClienteDB;
import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.core.userCases.exception.ErroValidacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VerficaCPFExistente implements ValidacaoCliente {

    @Override
    public void validar(Cliente cliente, ClienteDB banco) throws ErroValidacao {

        var busca = banco.buscarCPF(cliente.getCpf());
        if (busca.isPresent()) {
            throw new ErroValidacao("Usuario já cadastrado");
        }
    }
}
