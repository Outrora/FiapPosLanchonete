package br.com.lanchonete.drivers.web.cozinha;

import br.com.lanchonete.core.adapters.cozinha.FilaProdutoController;
import br.com.lanchonete.core.entities.FilaPedidos;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("cozinha")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Tag(name = "cozinha", description = "Endpoints do cozinha")
public class RestCozinha {

    @Inject
    FilaProdutoController driven;

    @GET
    public FilaPedidos pegarFilaComPedidos() {
        return driven.pegarFilaComPedidos();
    }
}
