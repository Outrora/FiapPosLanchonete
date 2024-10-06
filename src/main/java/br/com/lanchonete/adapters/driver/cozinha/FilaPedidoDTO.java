package br.com.lanchonete.adapters.driver.cozinha;

import br.com.lanchonete.adapters.driver.pedido.PedidoDTO;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Entity(name = "fila")
public class FilaPedidoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "fila")
    private Set<PedidoDTO> listaPedidos;


    private Date dia;
}
