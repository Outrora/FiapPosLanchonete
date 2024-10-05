package br.com.lanchonete.core.domain.entities;

import java.math.BigDecimal;
import java.util.List;

import br.com.lanchonete.core.domain.enums.Categoria;

public record Produto(
    String nome,
    String descricao,
    BigDecimal preco,
    Categoria categoria,
    List<String> listaDeImagens
) {
}
