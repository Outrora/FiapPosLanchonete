package br.com.lanchonete.drivers.db.pedido;

import br.com.lanchonete.core.entities.enums.EstadoPedido;
import br.com.lanchonete.drivers.db.cliente.ClienteDTO;
import br.com.lanchonete.drivers.db.cozinha.FilaPedidoDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "pedido")
public class PedidoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal preco;
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<PedidoProdutoDTO> pedidoProdutos;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true)
    private ClienteDTO cliente;

    @ManyToOne()
    private FilaPedidoDTO fila;

    @Enumerated(EnumType.STRING)
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<PedidoProdutoDTO> getPedidoProdutos() {
        return pedidoProdutos;
    }

    public void setPedidoProdutos(List<PedidoProdutoDTO> pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
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
