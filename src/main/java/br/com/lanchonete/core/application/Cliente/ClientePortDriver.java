package br.com.lanchonete.core.application.Cliente;


import br.com.lanchonete.adapters.driver.cliente.ClienteRepository;
import br.com.lanchonete.core.application.base.BasePortDiver;
import br.com.lanchonete.core.domain.entities.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@ApplicationScoped
public class ClientePortDriver extends BasePortDiver<Cliente, ClienteRepository> {


    private static final Logger log = LoggerFactory.getLogger(ClientePortDriver.class);

    @Transactional
    public void salvar(Cliente dados) {
        repository.cadastrarCliente(dados);
    }

    public Optional<Cliente> pegarCPF(String cpf) {
        var clienteDB = repository.buscarCPF(cpf);
        return clienteDB.map((clienteDB1 -> {
            return new Cliente(clienteDB1.nome, clienteDB1.email, clienteDB1.CPF);
        }));

    }

    public boolean alterar(Cliente dados) {
        System.out.println("Cliente");
        return true;
    }

    public boolean excluir(Cliente dados) {
        System.out.println("Cliente");
        return true;
    }


    public Optional<Cliente> pegarEmail(String email) {
        var clienteDB = repository.buscarEmail(email);
        return clienteDB.map((clienteDB1 -> {
            return new Cliente(clienteDB1.nome, clienteDB1.email, clienteDB1.CPF);
        }));
    }
}
