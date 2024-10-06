package br.com.lanchonete.adapters.driver.produto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.UUID;

@ApplicationScoped
@Entity
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "produto_UUID")
    public ProdutoDB produto;

    public String nome;
    public String url;
}
