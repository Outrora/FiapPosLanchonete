package br.com.lanchonete.drivers.mercadoPago;

public record ResponseMercadoPago(
        String in_store_order_id,
        String qr_data) {
}