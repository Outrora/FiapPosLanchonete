package br.com.lanchonete.core.domain.cliente;


import br.com.lanchonete.core.application.Cliente.ClientePortDriver;
import br.com.lanchonete.core.domain.base.ServiceBase;
import br.com.lanchonete.core.domain.cliente.validacoes.ValidacaoCliente;
import br.com.lanchonete.core.domain.entities.Cliente;
import br.com.lanchonete.core.domain.exception.ResultadaoVazioErro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class ServiceCliente implements ServiceBase<Cliente> {

    @Inject
    Instance<ValidacaoCliente> validadores;

    @Inject
    ClientePortDriver driver;

    public void salvarDados(Cliente dados) {
        for (var validador : validadores) {
            validador.validar(dados);
        }

        driver.salvar(dados);
    }

    public Optional<Cliente> pegarID(Long id) {
        return driver.pegarId(id);
    }

    public Cliente pegarCPF(String cpf) {

        var cliente = driver.pegarCPF(cpf);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        throw new ResultadaoVazioErro("Cliente não encontrado");

    }


}
