package br.com.lanchonete.core.userCases.cliente;

import java.util.List;

import br.com.lanchonete.core.adapters.Cliente.ClienteDB;
import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.core.userCases.base.UseCaseBase;
import br.com.lanchonete.core.userCases.cliente.validacoes.ValidacaoCliente;
import io.quarkus.arc.All;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SalvarClienteUseCase implements UseCaseBase {

    @Inject
    @All
    List<ValidacaoCliente> validadores;

    @Inject
    ClienteDB banco;

    public void salvarDados(Cliente dados) {

        for (var validador : validadores) {
            validador.validar(dados, banco);
        }

        banco.cadastrarCliente(dados);
    }

}
