package br.com.lanchonete.drivers.mercadoPago;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("mercado")
@RequestScoped
@Tag(name = "ercado Pago", description = "Api de WebHooks do mercado pago")
public class RestMercadoPaga {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void receberDados(Object dados) {
        System.out.println("Dados recebidos: " + dados);
    }

}
