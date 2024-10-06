package br.com.lanchonete.adapters.driver.pedido;

import br.com.lanchonete.adapters.driver.cliente.ClienteDTO;
import br.com.lanchonete.adapters.driver.cozinha.FilaPedidoDTO;
import br.com.lanchonete.adapters.driver.produto.ProdutoDTO;
import br.com.lanchonete.core.domain.enums.EstadoPedido;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity(name = "pedido")
public class PedidoDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BigDecimal preco;
    private Timestamp dataCriacao;

    @ManyToMany()
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private Set<ProdutoDTO> produtos;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteDTO cliente;

    @ManyToOne()
    private FilaPedidoDTO fila;

    private EstadoPedido estadoPedido;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Set<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }


    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
