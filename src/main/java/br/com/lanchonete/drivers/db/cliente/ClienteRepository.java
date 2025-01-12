package br.com.lanchonete.drivers.db.cliente;

import br.com.lanchonete.core.entities.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Timestamp;
import java.util.Optional;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<ClienteDTO> {

    public void cadastrarCliente(Cliente cliente) {
        var adaptered = new ClienteDTO();
        adaptered.alterarCliente(cliente);
        adaptered.setInclucao(new Timestamp(System.currentTimeMillis()));
        persist(adaptered);
    }

    public Optional<ClienteDTO> buscarCPF(String CPF) {
        return find("cpf", CPF).firstResultOptional();
    }

    public Optional<ClienteDTO> buscarEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public Optional<ClienteDTO> buscarID(Long id) {
        return findByIdOptional(id);
    }
}
