package br.com.lanchonete.core.adapters.Cliente;

import java.util.Optional;

import br.com.lanchonete.core.entities.Cliente;


public interface ClienteDB {

    void cadastrarCliente(Cliente dados);

    Optional<Cliente> buscarCPF(String cpf);

    Optional<Cliente> buscarEmail(String email);

    Optional<Cliente> buscarID(Long id);
}
