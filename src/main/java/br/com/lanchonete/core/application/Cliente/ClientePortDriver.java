package br.com.lanchonete.core.application.Cliente;


import br.com.lanchonete.core.application.base.BasePortDiver;
import br.com.lanchonete.core.domain.entities.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@ApplicationScoped
public class ClientePortDriver extends BasePortDiver<Cliente, ClienteBanco> {


    private static final Logger log = LoggerFactory.getLogger(ClientePortDriver.class);

    @Transactional
    public void salvar(Cliente dados) {
        repository.cadastrarCliente(dados);
    }

    @Override
    public Optional<Cliente> pegarId(Long id) {
        return repository.buscarID(id);
    }


    public Optional<Cliente> pegarCPF(String cpf) {
        return repository.buscarCPF(cpf);
    }


    public Optional<Cliente> pegarEmail(String email) {
        return repository.buscarEmail(email);
    }
}
