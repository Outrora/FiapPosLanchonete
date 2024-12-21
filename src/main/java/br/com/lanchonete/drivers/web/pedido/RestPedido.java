package br.com.lanchonete.drivers.web.pedido;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.lanchonete.core.adapters.Pedido.PedidoController;
import br.com.lanchonete.core.adapters.Pedido.PedidoRequest;

import java.util.UUID;

@Path("pedido")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Tag(name = "pedido", description = "Endpoints do pedido")
public class RestPedido {

    @Inject
    PedidoController portDriven;

    @POST
    public UUID criarPedido(PedidoRequest pedido) {
        return portDriven.cadatrarPedido(pedido);
    }
}
