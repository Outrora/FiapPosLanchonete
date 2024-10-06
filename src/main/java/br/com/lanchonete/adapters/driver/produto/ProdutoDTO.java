package br.com.lanchonete.adapters.driver.produto;

import br.com.lanchonete.adapters.driver.pedido.PedidoDTO;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@ApplicationScoped
@Entity(name = "Produto")
public class ProdutoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<ImagemProdutoDTO> listaDeImagens;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "produtos")
    private Set<PedidoDTO> pedidos;

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

    public void setListaDeImagens(Set<ImagemProdutoDTO> listaDeImagens) {
        this.listaDeImagens = listaDeImagens;
    }

    public void setPedidos(Set<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public ProdutoDTO(Produto produto) {
        this.nome = produto.nome();
        this.descricao = produto.descricao();
        this.preco = produto.preco();
        this.categoria = produto.categoria();
        if (produto.id().isPresent()) {
            this.id = produto.id().get();
        }
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

    public Set<ImagemProdutoDTO> getListaDeImagens() {
        return listaDeImagens;
    }

    public Set<PedidoDTO> getPedidos() {
        return pedidos;
    }
}
