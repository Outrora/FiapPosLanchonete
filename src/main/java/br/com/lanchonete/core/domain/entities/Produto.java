package br.com.lanchonete.core.domain.entities;

import br.com.lanchonete.core.domain.enums.Categoria;

import java.math.BigDecimal;
import java.util.Optional;

public class Produto {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;
    private Optional<Long> id;


    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria, Optional<Long> id) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.id = id;
    }

    public void alteraId(Long id) {
        this.id = Optional.of(id);

    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Optional<Long> getId() {
        return id;
    }

}
