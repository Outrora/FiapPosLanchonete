package br.com.lanchonete.core.domain.entities;

import br.com.lanchonete.core.domain.enums.EstadoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record Pedido(
        LocalDateTime dataCriacao,
        Cliente cliente,
        List<Produto> produtos,
        EstadoPedido estadoPedido,
        BigDecimal valorTotal
) {
} 
  

  

