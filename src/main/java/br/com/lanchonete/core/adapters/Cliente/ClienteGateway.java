package br.com.lanchonete.core.adapters.Cliente;

import br.com.lanchonete.core.adapters.base.BaseGateway;
import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.drivers.AplicacaoMapper;
import br.com.lanchonete.drivers.db.cliente.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class ClienteGateway extends BaseGateway implements ClienteDB {

    @Inject
    ClienteRepository repository;

    @Transactional
    @Override
    public void cadastrarCliente(Cliente dados) {
        repository.cadastrarCliente(dados);
    }

    @Override
    public Optional<Cliente> buscarCPF(String cpf) {
        LOG.info("Buscando Cliente pelo CPF");
        return repository.buscarCPF(cpf).map(AplicacaoMapper.INSTANCE::toCliente);
    }

    @Override
    public Optional<Cliente> buscarEmail(String email) {
        return repository.buscarEmail(email).map(AplicacaoMapper.INSTANCE::toCliente);
    }

    @Override
    public Optional<Cliente> buscarID(Long id) {
        LOG.info("Buscando Cliente pelo ID");
        return repository.buscarID(id).map(AplicacaoMapper.INSTANCE::toCliente);
    }
}
