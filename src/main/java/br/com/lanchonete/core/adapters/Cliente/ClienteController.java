package br.com.lanchonete.core.adapters.Cliente;

import br.com.lanchonete.core.adapters.base.BaseController;
import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.core.userCases.cliente.BuscarClienteUseCase;
import br.com.lanchonete.core.userCases.cliente.SalvarClienteUseCase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ClienteController extends BaseController {

    @Inject
    SalvarClienteUseCase salvarCliente;

    @Inject
    BuscarClienteUseCase buscarCliente;

    @Inject
    ClienteDB gateway;

    public Cliente pegarCPF(String CPF) {
        return buscarCliente.pegarCPF(CPF);
    }

    public void salvar(ClienteRequest cliente) {
        LOG.info("Iniciando o salvamento de dados");
        salvarCliente.salvarDados(cliente.toCliente());
        LOG.info("Finalizado o salvamento de dados");
    }

}
