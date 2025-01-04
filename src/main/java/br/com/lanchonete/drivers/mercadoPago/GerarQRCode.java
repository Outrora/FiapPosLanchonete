package br.com.lanchonete.drivers.mercadoPago;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "mercado-pago")
@Path("/instore/orders/qr/seller/collectors")
public interface GerarQRCode {

    @POST
    @Path("/{userId}/pos/{posId}/qrs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ResponseMercadoPago criarPagamento(@PathParam("userId") String userId, @PathParam("posId") String posId,
            @HeaderParam("Authorization") String authorization,
            RequestMercadoPago request);

}
