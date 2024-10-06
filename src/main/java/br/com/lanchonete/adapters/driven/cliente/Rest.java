package br.com.lanchonete.adapters.driven.cliente;

import br.com.lanchonete.core.application.Cliente.ClienteDTO;
import br.com.lanchonete.core.application.Cliente.ClienteService;
import br.com.lanchonete.core.domain.entities.Cliente;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

@Path("cliente")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Tag(name = "Cliente", description = "Endpoints do clientes")
public class Rest {


    @Inject
    ClienteService service;

    @GET
    @Path("{CPF}")
    public Cliente pegarCPF(String CPF) {
        return service.pegarCPF(CPF);
    }

    @POST
    public RestResponse<String> inserirCliente(ClienteDTO cliente) {
        service.inserirCliente(cliente);
        return ResponseBuilder.ok("Cadastrado com Sucesso").status(201).build();
    }

}
