package br.com.lanchonete.core.application.Cliente;

import br.com.lanchonete.core.domain.cliente.PersistenciaCliente;
import br.com.lanchonete.core.domain.entities.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClienteService {

    @Inject
    PersistenciaCliente persitencia;


    public Cliente pegarCPF(String CPF) {

        return persitencia.pegarCPF(CPF);
    }


    public void inserirCliente(ClienteDTO clienteDTO) {
        System.out.println(clienteDTO.CPF());
        var cpfLimpo = clienteDTO.CPF().replaceAll("[-.]", "").trim();
        Cliente cliente = new Cliente(clienteDTO.nome(), clienteDTO.email(), cpfLimpo);
        persitencia.salvarDados(cliente);
    }
}
