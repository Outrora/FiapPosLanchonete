package br.com.lanchonete.drivers.web.pedido;

import br.com.lanchonete.core.adapters.Pedido.PedidoController;
import br.com.lanchonete.core.adapters.Pedido.PedidoRequest;
import br.com.lanchonete.drivers.web.helpers.HeaderAuthorizationDecoder;
import io.jsonwebtoken.Claims;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Path("pedido")
@RequestScoped
@Tag(name = "pedido", description = "Endpoints do pedido")
public class RestPedido {

    protected final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Inject
    PedidoController controller;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarPedido(@Context HttpHeaders headers, PedidoRequest pedido) {
        Long userId = null;

        if (headers.getHeaderString(HttpHeaders.AUTHORIZATION) != null) {
            var claim = HeaderAuthorizationDecoder.decode(headers);
            userId = this.parseId(claim).orElseThrow();
        }

        if (userId == null) {
            userId = 0L;
        }

        var retorno = controller.cadatrarPedido(pedido, userId);
        return Response
                .status(Response.Status.CREATED).entity(retorno).build();

    }

    private Optional<Long> parseId(Claims claim) {
        try {
            LOG.severe("ID: " + claim.get("id", String.class));
            return Optional.of(Long.parseLong(claim.get("id", String.class)));
        } catch (Exception e) {
            return Optional.empty();
        }
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
