package br.com.lanchonete.core.userCases.produto;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.entities.enums.Categoria;

public class ProdutoAdapter {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;

    public ProdutoAdapter(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
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

    public Produto adapter() {
        return new Produto(nome, descricao, preco, categoria, Optional.empty());
    }
}
