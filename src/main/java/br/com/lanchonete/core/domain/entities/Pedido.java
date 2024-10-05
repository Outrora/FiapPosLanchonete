package br.com.lanchonete.core.domain.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import br.com.lanchonete.core.domain.enums.EstadoPedido;

public record Pedido(
     Timestamp dataCriacao,
     Cliente cliente,
     List<Produto> produtos,
     EstadoPedido estadoPedido,
     BigDecimal valorTotal 
) {
} 
  

  

