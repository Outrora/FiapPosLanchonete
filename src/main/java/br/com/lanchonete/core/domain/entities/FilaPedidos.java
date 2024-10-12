package br.com.lanchonete.core.domain.entities;

import java.sql.Date;
import java.util.List;

public record FilaPedidos(
        List<Pedido> listaPedidos,
        Date dia,
        Long id
) {

}
