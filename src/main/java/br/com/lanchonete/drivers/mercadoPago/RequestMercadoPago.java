package br.com.lanchonete.drivers.mercadoPago;

import java.util.List;

public record RequestMercadoPago(
                String title,
                String external_reference,
                String description,
                double total_amount,
                List<Item> items) {
        public static record Item(
                        String title,
                        double unit_price,
                        int quantity,
                        String unit_measure,
                        double total_amount) {
        }
}
