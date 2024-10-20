package br.com.lanchonete.core.application.Cliente;

import br.com.lanchonete.core.domain.entities.Cliente;

import java.util.Optional;

public interface ClienteBanco {

    void cadastrarCliente(Cliente dados);

    Optional<Cliente> buscarCPF(String cpf);

    Optional<Cliente> buscarEmail(String email);

    Optional<Cliente> buscarID(Long id);
}
