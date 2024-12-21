package br.com.lanchonete.drivers.db.cliente;


import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.drivers.db.pedido.PedidoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

@ApplicationScoped
@Entity(name = "cliente")
public class ClienteDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private Timestamp inclucao;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PedidoDTO> pedidoDTO;


    public void alterarCliente(Cliente cliente) {
        if (cliente.getId().isPresent()) {
            id = cliente.getId().get();
        }
        nome = cliente.getNome();
        email = cliente.getEmail();
        cpf = cliente.getCpf();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Timestamp getInclucao() {
        return inclucao;
    }

    public void setInclucao(Timestamp inclucao) {
        this.inclucao = inclucao;
    }

    public Set<PedidoDTO> getPedidoDTO() {
        return pedidoDTO;
    }

    public void setPedidoDTO(Set<PedidoDTO> pedidoDTO) {
        this.pedidoDTO = pedidoDTO;
    }
}
