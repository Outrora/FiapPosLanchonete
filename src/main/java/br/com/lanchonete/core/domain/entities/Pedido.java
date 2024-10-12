package br.com.lanchonete.core.domain.entities;

import br.com.lanchonete.core.domain.enums.EstadoPedido;
import org.antlr.v4.runtime.misc.Pair;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private LocalDateTime dataCriacao;
    private Cliente cliente;
    private List<Pair<Integer, Produto>> produtos;
    private EstadoPedido estadoPedido;
    private BigDecimal valorTotal;


    public Pedido(LocalDateTime dataCriacao, Cliente cliente, List<Pair<Integer, Produto>> produtos, EstadoPedido estadoPedido, BigDecimal valorTotal) {
        this.dataCriacao = dataCriacao;
        this.cliente = cliente;
        this.produtos = produtos;
        this.estadoPedido = estadoPedido;
        this.valorTotal = valorTotal;
    }

    public List<Pair<Integer, Produto>> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Pair<Integer, Produto>> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }


    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void calcularValorTotal() {
        this.valorTotal = BigDecimal.ZERO;
        for (var produto : produtos) {
            var preco = produto.b.getPreco().multiply(BigDecimal.valueOf(produto.a));
            this.valorTotal = this.valorTotal.add(preco);
        }
    }

    public static Pedido ofNovo(Cliente cliente, List<Pair<Integer, Produto>> produtos) {
        return new Pedido(
                LocalDateTime.now(),
                cliente,
                produtos,
                EstadoPedido.recebido,
                BigDecimal.ZERO
        );
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "dataCriacao=" + dataCriacao +
                ", cliente=" + cliente +
                ", produtos=" + produtos +
                ", estadoPedido=" + estadoPedido +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
  

  

