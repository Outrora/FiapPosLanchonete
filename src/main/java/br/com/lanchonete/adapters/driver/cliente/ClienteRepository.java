package br.com.lanchonete.adapters.driver.cliente;

import br.com.lanchonete.core.domain.entities.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Timestamp;
import java.util.Optional;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<ClienteDB> {

    public Optional<ClienteDB> buscarCPF(String CPF) {
        return find("CPF", CPF).firstResultOptional();
    }

    public void cadastrarCliente(Cliente cliente) {
        var adaptered = this.adapterDB(cliente);
        adaptered.inclucao = new Timestamp(System.currentTimeMillis());
        persist(adaptered);
    }


    private ClienteDB adapterDB(Cliente cliente) {
        ClienteDB peristencia = new ClienteDB();
        peristencia.CPF = cliente.CPF();
        peristencia.email = cliente.email();
        peristencia.nome = cliente.nome();
        return peristencia;
    }

    public Optional<ClienteDB> buscarEmail(String email) {
        return find("email", email).firstResultOptional();
    }
}
