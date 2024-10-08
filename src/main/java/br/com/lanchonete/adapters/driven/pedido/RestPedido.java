package br.com.lanchonete.adapters.driven.pedido;

import br.com.lanchonete.core.domain.entities.Pedido;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("pedido")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Tag(name = "pedido", description = "Endpoints do pedido")
public class RestPedido {

    @POST
    public void pegarCPF(Pedido pedido) {

    }
}
