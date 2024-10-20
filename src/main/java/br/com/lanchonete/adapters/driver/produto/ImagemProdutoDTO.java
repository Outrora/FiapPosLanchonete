package br.com.lanchonete.adapters.driver.produto;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "produto_imagem")
public class ImagemProdutoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "produto_UUID")
    public ProdutoDTO produto;

    public String nome;
    public String url;
}
