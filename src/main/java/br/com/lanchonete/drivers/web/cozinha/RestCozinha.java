package br.com.lanchonete.drivers.web.cozinha;

import br.com.lanchonete.core.adapters.cozinha.FilaProdutoController;
import br.com.lanchonete.core.entities.FilaPedidos;
import br.com.lanchonete.core.entities.Pedido;
import io.vertx.core.cli.annotations.Description;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("cozinha")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charsert=utf-8")
@Tag(name = "cozinha", description = "Endpoints do cozinha")
public class RestCozinha {

    @Inject
    FilaProdutoController controller;

    @GET
    @Path("ListaPedidos")
    @Description("Retorna a lista com todos pedidos")
    public FilaPedidos pegarLista() {
        return controller.pegarFilaComPedidos();
    }

    @GET
    @Path("FilaPedidos")
    @Description("Retorna a lista com todos pedidos pronto para serem preparados")
    public List<Pedido> pegarFilaComPedidos() {
        return controller.pegarFilaComPedidosEmPreparo();
    }
}
