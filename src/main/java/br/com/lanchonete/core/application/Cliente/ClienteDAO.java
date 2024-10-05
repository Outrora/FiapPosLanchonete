package br.com.lanchonete.core.application.Cliente;

import java.util.List;

import br.com.lanchonete.core.application.interfaces.BaseDAO;
import br.com.lanchonete.core.domain.entities.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteDAO implements BaseDAO<Cliente> {

    @Override
    public Cliente salvar(Cliente dados) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    @Override
    public boolean alterar(Cliente dados) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterar'");
    }

    @Override
    public boolean excluir(Cliente dados) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

    @Override
    public List<Cliente> ListarTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ListarTodos'");
    }

    

}
