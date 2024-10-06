package br.com.lanchonete.core.application.Produto;

import br.com.lanchonete.core.application.base.AdapterBase;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;

import java.math.BigDecimal;
import java.util.Optional;

public class ProdutoAdapter implements AdapterBase<Produto> {
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

    @Override
    public Produto adapter() {
        return new Produto(nome, descricao, preco, categoria, Optional.empty());
    }
}
