package br.com.lanchonete.adapters.driver.cliente;

import br.com.lanchonete.adapters.AplicacaoMapper;
import br.com.lanchonete.core.application.Cliente.ClienteBanco;
import br.com.lanchonete.core.domain.entities.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Timestamp;
import java.util.Optional;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<ClienteDTO>, ClienteBanco {


    public void cadastrarCliente(Cliente cliente) {
        var adaptered = AplicacaoMapper.INSTANCE.toCliente(cliente);
        adaptered.inclucao = new Timestamp(System.currentTimeMillis());
        persist(adaptered);
    }

    public Optional<Cliente> buscarCPF(String CPF) {
        return find("cpf", CPF).firstResultOptional().map(AplicacaoMapper.INSTANCE::toCliente);
    }

    public Optional<Cliente> buscarEmail(String email) {
        return find("email", email).firstResultOptional().map(AplicacaoMapper.INSTANCE::toCliente);
    }
}
