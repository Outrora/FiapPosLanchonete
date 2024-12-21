package br.com.lanchonete.core.entities;

import org.antlr.v4.runtime.misc.Pair;

import br.com.lanchonete.core.entities.enums.EstadoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Pedido {
    private LocalDateTime dataCriacao;
    private Optional<Cliente> cliente;
    private List<Pair<Integer, Produto>> produtos;
    private EstadoPedido estadoPedido;
    private BigDecimal valorTotal;


    public Pedido(LocalDateTime dataCriacao, Optional<Cliente> cliente, List<Pair<Integer, Produto>> produtos, EstadoPedido estadoPedido, BigDecimal valorTotal) {
        this.dataCriacao = dataCriacao;
        this.cliente = cliente;
        this.produtos = produtos;
        this.estadoPedido = estadoPedido;
        this.valorTotal = valorTotal;
    }

    public List<Pair<Integer, Produto>> getProdutos() {
        return produtos;
    }

    public Optional<Cliente> getCliente() {
        return cliente;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
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

    public static Pedido ofNovo(Optional<Cliente> cliente, List<Pair<Integer, Produto>> produtos) {
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
  

  

