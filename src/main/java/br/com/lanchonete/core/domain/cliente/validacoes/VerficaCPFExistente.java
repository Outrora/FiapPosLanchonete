package br.com.lanchonete.core.domain.cliente.validacoes;

import br.com.lanchonete.core.application.Cliente.ClientePortDriver;
import br.com.lanchonete.core.domain.entities.Cliente;
import br.com.lanchonete.core.domain.exception.ErroValidacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VerficaCPFExistente implements ValidacaoCliente {

    @Inject
    ClientePortDriver clientePortDriver;

    @Override
    public void validar(Cliente cliente) throws ErroValidacao {

        var busca = clientePortDriver.pegarCPF(cliente.getCpf());
        if (busca.isPresent()) {
            throw new ErroValidacao("Usuario já cadastrado");
        }
    }
}
