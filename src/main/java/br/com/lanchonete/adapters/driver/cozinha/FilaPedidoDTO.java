package br.com.lanchonete.adapters.driver.cozinha;

import br.com.lanchonete.adapters.driver.pedido.PedidoDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "fila")
public class FilaPedidoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "fila")
    private Set<PedidoDTO> listaPedidos;

    private LocalDate dia;

    public Set<PedidoDTO> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(Set<PedidoDTO> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }
}
