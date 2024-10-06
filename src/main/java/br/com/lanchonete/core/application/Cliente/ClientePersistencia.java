package br.com.lanchonete.core.application.Cliente;


import br.com.lanchonete.adapters.driver.cliente.ClienteRepository;
import br.com.lanchonete.core.application.interfaces.BaseDAO;
import br.com.lanchonete.core.domain.entities.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@ApplicationScoped
public class ClientePersistencia implements BaseDAO<Cliente> {

    @Inject
    ClienteRepository clienteRepository;

    private static final Logger log = LoggerFactory.getLogger(ClientePersistencia.class);

    @Transactional
    public void salvar(Cliente dados) {
        clienteRepository.cadastrarCliente(dados);
    }


    public Optional<Cliente> pegarCPF(String cpf) {
        var clienteDB = clienteRepository.buscarCPF(cpf);
        return clienteDB.map((clienteDB1 -> {
            return new Cliente(clienteDB1.nome, clienteDB1.email, clienteDB1.CPF);
        }));

    }

    @Override
    public boolean alterar(Cliente dados) {
        System.out.println("Cliente");
        return true;
    }

    @Override
    public boolean excluir(Cliente dados) {
        System.out.println("Cliente");
        return true;
    }


    public Optional<Cliente> pegarEmail(String email) {
        var clienteDB = clienteRepository.buscarEmail(email);
        return clienteDB.map((clienteDB1 -> {
            return new Cliente(clienteDB1.nome, clienteDB1.email, clienteDB1.CPF);
        }));
    }
}
