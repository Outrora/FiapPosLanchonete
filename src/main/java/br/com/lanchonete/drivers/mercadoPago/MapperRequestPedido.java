package br.com.lanchonete.drivers.mercadoPago;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.lanchonete.core.entities.Pedido;

public class MapperRequestPedido {

    public static RequestMercadoPago map(Pedido pedido) {
        RequestMercadoPago requestPedido = new RequestMercadoPago(
                "Pedido_Compra",
                pedido.getId().map(UUID::toString).orElseGet(() -> "0"),
                pedido.getDataCriacao().toString(),
                pedido.getValorTotal().doubleValue(),
                pedido.getProdutos().stream().map((pedidoItem) -> {

                    var total = pedidoItem.b.getPreco().multiply(BigDecimal.valueOf(pedidoItem.a)).doubleValue();
                    var request = new RequestMercadoPago.Item(
                            pedidoItem.b.getNome(),
                            pedidoItem.b.getPreco().doubleValue(),
                            pedidoItem.a,
                            "unit",
                            total);

                    return request;
                }).toList());

        return requestPedido;
    }

}
