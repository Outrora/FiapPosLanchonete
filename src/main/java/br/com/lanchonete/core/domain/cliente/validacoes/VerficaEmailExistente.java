package br.com.lanchonete.core.domain.cliente.validacoes;

import br.com.lanchonete.core.application.Cliente.ClientePersistencia;
import br.com.lanchonete.core.domain.entities.Cliente;
import br.com.lanchonete.core.domain.exception.ErroValidacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VerficaEmailExistente implements ValidacaoCliente {

    @Inject
    ClientePersistencia clientePersistencia;

    @Override
    public void validar(Cliente cliente) throws ErroValidacao {

        var busca = clientePersistencia.pegarEmail(cliente.email());
        if (busca.isPresent()) {
            throw new ErroValidacao("Email já cadastrado");
        }
    }
}
