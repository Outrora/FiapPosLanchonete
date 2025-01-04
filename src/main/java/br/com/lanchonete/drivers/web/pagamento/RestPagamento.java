package br.com.lanchonete.drivers.web.pagamento;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.lanchonete.core.adapters.Pagamento.PagamentoController;
import br.com.lanchonete.core.adapters.Pagamento.PagamentoWebHookResquest;
import br.com.lanchonete.drivers.web.base.RestBase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("pedido")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@Tag(name = "pagamento", description = "Endpoints para pagemento")
public class RestPagamento extends RestBase<PagamentoController> {

    @POST
    @Path("webhookPagamento")
    @Consumes(MediaType.APPLICATION_JSON)
    public void webhookPagamento(PagamentoWebHookResquest request) {

        controller.processarWebhookPagamento(request);

    }

}
