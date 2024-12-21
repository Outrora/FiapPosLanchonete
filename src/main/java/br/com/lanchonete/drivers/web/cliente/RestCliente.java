package br.com.lanchonete.drivers.web.cliente;

import br.com.lanchonete.core.adapters.Cliente.ClienteController;
import br.com.lanchonete.core.adapters.Cliente.ClienteRequest;
import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.drivers.web.base.RestBase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("cliente")
@RequestScoped
@Tag(name = "Cliente", description = "Endpoints do clientes")
public class RestCliente extends RestBase<ClienteController> {

    @GET
    @Path("{cpf}")
    public Cliente pegarCPF(@PathParam("cpf") String cpf) {
        return controller.pegarCPF(cpf);
    }

    @POST
    public Response inserir(ClienteRequest cliente) {
        controller.salvar(cliente);
        return respostaSucesso();
    }

}
