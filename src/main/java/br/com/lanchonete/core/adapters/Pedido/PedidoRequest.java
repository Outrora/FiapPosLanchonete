package br.com.lanchonete.core.adapters.Pedido;

import java.util.List;

public class PedidoRequest {
    List<produtoQuantidade> produtos;


    public List<produtoQuantidade> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<produtoQuantidade> produtos) {
        this.produtos = produtos;
    }

    public record produtoQuantidade(Integer quandidade, Long id) {
    }

}
