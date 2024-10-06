package br.com.lanchonete.adapters.driver.produto;

import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<ProdutoDTO> {


    public List<ProdutoDTO> buscarPorCatergoria(Categoria categoria) {
        return find("categoria", categoria).list();
    }

    public void cadastrarProduto(Produto produto) {
        var adaptered = new ProdutoDTO(produto);
        persist(adaptered);
    }

    public void editarProduto(Produto produto) {
        if (produto.id().isPresent()) {
            var adaptered = new ProdutoDTO(produto);
            persist(adaptered);
        }
    }

    public boolean removerProduto(Long id) {
        return deleteById(id);
    }


}
