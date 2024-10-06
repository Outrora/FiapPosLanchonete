package br.com.lanchonete.core.application.Cliente;

import br.com.lanchonete.core.application.base.BasePortDriven;
import br.com.lanchonete.core.domain.cliente.ServiceCliente;
import br.com.lanchonete.core.domain.entities.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientePortDriven extends BasePortDriven<ClienteAdapter, ServiceCliente> {


    public Cliente pegarCPF(String CPF) {
        return service.pegarCPF(CPF);
    }

}
