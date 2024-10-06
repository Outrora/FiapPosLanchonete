package br.com.lanchonete.core.domain.entities;

import br.com.lanchonete.core.domain.enums.Categoria;

import java.math.BigDecimal;
import java.util.Optional;

public record Produto(
        String nome,
        String descricao,
        BigDecimal preco,
        Categoria categoria,
        Optional<Long> id
) {
}
