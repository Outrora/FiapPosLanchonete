package br.com.lanchonete.core.userCases.cliente;

import br.com.lanchonete.core.adapters.Cliente.ClienteDB;

import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.core.userCases.base.UseCaseBase;

import br.com.lanchonete.core.userCases.exception.ResultadoVazioErro;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class BuscarClienteUseCase implements UseCaseBase {

    @Inject
    ClienteDB banco;

    public Optional<Cliente> pegarID(Long id) {
        return banco.buscarID(id);
    }

    public Cliente pegarCPF(String cpf) {

        var cliente = banco.buscarCPF(cpf);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        throw new ResultadoVazioErro("Cliente não encontrado");

    }

}
