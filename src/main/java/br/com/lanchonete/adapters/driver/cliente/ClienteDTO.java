package br.com.lanchonete.adapters.driver.cliente;


import br.com.lanchonete.adapters.driver.pedido.PedidoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
@Entity(name = "cliente")
public class ClienteDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    public String nome;
    public String email;
    public String CPF;
    public Timestamp inclucao;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PedidoDTO> pedidoDTO;


}
