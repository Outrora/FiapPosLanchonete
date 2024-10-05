package br.com.lanchonete.core.domain.cliente;

import java.util.List;

import br.com.lanchonete.core.application.Cliente.ClienteDAO;
import br.com.lanchonete.core.domain.cliente.validacoes.ValidacaoCliente;
import br.com.lanchonete.core.domain.entities.Cliente;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;


public class persistenciaCliente {

    @Inject
    Instance<ValidacaoCliente> validadores;

    @Inject 
    ClienteDAO clienteDAO;

   
    public Cliente salvarDados(Cliente dados){
        for (var validador: validadores ){
            validador.validar(dados);
        }

        return clienteDAO.salvar(dados);
    }

 
    public boolean alterarDados(Cliente dados) {
        for (var validador: validadores ){
            validador.validar(dados);
        }
        return clienteDAO.alterar(dados);
    }

  
    public boolean excluirDados(Cliente dados) {
      return clienteDAO.excluir(dados);
    }

   
    public List<Cliente> todosDados() {
        return clienteDAO.ListarTodos();
    }

}
