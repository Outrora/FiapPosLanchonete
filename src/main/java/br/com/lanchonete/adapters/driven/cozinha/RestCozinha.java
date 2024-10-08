package br.com.lanchonete.adapters.driven.cozinha;


import br.com.lanchonete.core.domain.entities.FilaPedidos;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
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

    @POST
    public void pegarCPF(FilaPedidos filaPedidos) {

    }
}
