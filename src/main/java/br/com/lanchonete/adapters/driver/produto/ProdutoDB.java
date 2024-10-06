package br.com.lanchonete.adapters.driver.produto;

import br.com.lanchonete.core.domain.enums.Categoria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
@Entity(name = "Produto")
public class ProdutoDB {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    public String nome;
    public String descricao;
    public BigDecimal preco;
    public Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    public Set<ImagemProduto> listaDeImagens;
}
