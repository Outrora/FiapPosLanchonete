package br.com.lanchonete.core.adapters.Pagamento;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.drivers.mercadoPago.GerarQRCode;
import br.com.lanchonete.drivers.mercadoPago.MapperRequestPedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PagamentoMercadoPagoGateway implements IPagamentoGateways {

    @ConfigProperty(name = "mercado_pago.user.id")
    String idUser;

    @ConfigProperty(name = "mercado_pago.pos.id")
    String idPOS;

    @ConfigProperty(name = "mercado_pago.token")
    String token;

    @Inject
    @RestClient
    GerarQRCode gerarQRCode;

    @Override
    public void criarPagamento(Pedido pedido) {
        String bearerToken = "Bearer " + token;
        System.out.println("Criando pagamento para o pedido: " + bearerToken);
        var resutado = gerarQRCode.criarPagamento(idUser, idPOS, bearerToken, MapperRequestPedido.map(pedido));
        System.out.println("Pagamento criado: " + resutado);
    }

}
