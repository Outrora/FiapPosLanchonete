package br.com.lanchonete.core.domain.entities;

import java.time.LocalDate;
import java.util.List;

public record FilaPedidos(
        List<Pedido> listaPedidos,
        LocalDate dia,
        Long id
) {

}
