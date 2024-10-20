package br.com.lanchonete.adapters.driver.pedido;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class PedidoProdutoId implements Serializable {
    private UUID pedido;
    private Long produto;

    public UUID getPedido() {
        return pedido;
    }

    public void setPedido(UUID pedido) {
        this.pedido = pedido;
    }

    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoProdutoId)) return false;
        PedidoProdutoId that = (PedidoProdutoId) o;
        return Objects.equals(pedido, that.pedido) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }
}
