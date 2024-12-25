package br.com.lanchonete.drivers.web.pedido;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.lanchonete.core.adapters.Pedido.PedidoController;
import br.com.lanchonete.core.adapters.Pedido.PedidoRequest;

import java.util.UUID;

@Path("pedido")
@RequestScoped
@Tag(name = "pedido", description = "Endpoints do pedido")
public class RestPedido {

    @Inject
    PedidoController controller;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UUID criarPedido(PedidoRequest pedido) {
        return controller.cadatrarPedido(pedido);
    }

    @GET
    @Path("statusPagamento/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @APIResponse(responseCode = "200", description = "Retorna o status de pagamento do Pedido", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class, example = "Pagamento Aprovado")))
    @APIResponse(responseCode = "404", description = "Retorna quando não encontrou pedido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class, example = "Pedido não encontrado")))

    public String verificarStatusPedido(UUID id) {
        return controller.verficarStatusPedido(id);
    }
}
