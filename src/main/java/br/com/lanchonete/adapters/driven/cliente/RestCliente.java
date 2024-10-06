package br.com.lanchonete.adapters.driven.cliente;

import br.com.lanchonete.adapters.driven.base.RestBase;
import br.com.lanchonete.core.application.Cliente.ClienteAdapter;
import br.com.lanchonete.core.application.Cliente.ClientePortDriven;
import br.com.lanchonete.core.domain.entities.Cliente;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("cliente")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Tag(name = "Cliente", description = "Endpoints do clientes")
public class RestCliente extends RestBase<ClienteAdapter, ClientePortDriven> {


    @GET
    @Path("{CPF}")
    public Cliente pegarCPF(@PathParam("CPF") String CPF) {
        return driven.pegarCPF(CPF);
    }


}
