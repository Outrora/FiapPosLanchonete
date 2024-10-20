package br.com.lanchonete.adapters.driver.pedido;

import br.com.lanchonete.adapters.driver.produto.ProdutoDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "pedido_produto")
public class PedidoProdutoDTO {

    @EmbeddedId
    private PedidoProdutoId id;

    @ManyToOne
    @MapsId("pedido")
    @JoinColumn(name = "pedido_id")
    private PedidoDTO pedido;

    @ManyToOne
    @MapsId("produto")
    @JoinColumn(name = "produto_id")
    private ProdutoDTO produto;

    private int quantidade;

    public PedidoProdutoId getId() {
        return id;
    }

    public void setId(PedidoProdutoId id) {
        this.id = id;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
