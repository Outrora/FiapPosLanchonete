package br.com.lanchonete.core.domain.cliente;


import br.com.lanchonete.core.application.Cliente.ClientePersistencia;
import br.com.lanchonete.core.domain.cliente.validacoes.ValidacaoCliente;
import br.com.lanchonete.core.domain.entities.Cliente;
import br.com.lanchonete.core.domain.exception.ResultadaoVazioErro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class PersistenciaCliente {

    @Inject
    Instance<ValidacaoCliente> validadores;

    @Inject
    ClientePersistencia clienteDAO;

    public void salvarDados(Cliente dados) {
        for (var validador : validadores) {
            validador.validar(dados);
        }

        clienteDAO.salvar(dados);
    }

    public Cliente pegarCPF(String cpf) {

        var cliente = clienteDAO.pegarCPF(cpf);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        throw new ResultadaoVazioErro("Cliente não encontrado");

    }

    public boolean alterarDados(Cliente dados) {
        for (var validador : validadores) {
            validador.validar(dados);
        }
        return clienteDAO.alterar(dados);
    }

    public boolean excluirDados(Cliente dados) {
        return clienteDAO.excluir(dados);
    }

}
