package br.com.lanchonete.core.adapters.Pedido;

import java.util.List;

public class PedidoRequest {
    Long id_cliente;
    List<produtoQuantidade> produtos;


    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public List<produtoQuantidade> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<produtoQuantidade> produtos) {
        this.produtos = produtos;
    }

    public record produtoQuantidade(Integer quandidade, Long id) {
    }

}
