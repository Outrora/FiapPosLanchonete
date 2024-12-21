package br.com.lanchonete.drivers.db.produto;

import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.entities.enums.Categoria;
import br.com.lanchonete.drivers.db.pedido.PedidoProdutoDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity(name = "Produto")
public class ProdutoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<ImagemProdutoDTO> listaDeImagens;

    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PedidoProdutoDTO> pedidoProdutos;


    public void alteraDados(Produto produto) {
        categoria = produto.getCategoria();
        descricao = produto.getDescricao();
        nome = produto.getNome();
        preco = produto.getPreco();
    }

    public List<PedidoProdutoDTO> getPedidoProdutos() {
        return pedidoProdutos;
    }

    public void setPedidoProdutos(List<PedidoProdutoDTO> pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutoDTO() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public Long getId() {
        return id;
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


}
