package br.com.lanchonete.adapters.driver.produto;

import br.com.lanchonete.adapters.AplicacaoMapper;
import br.com.lanchonete.core.application.Produto.ProdutoBanco;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<ProdutoDTO>, ProdutoBanco {


    public List<ProdutoDTO> buscarPorCatergoria(Categoria categoria) {
        return find("categoria", categoria).list();
    }

    public void cadastrarProduto(Produto produto) {
        var adaptered = AplicacaoMapper.INSTANCE.toProduto(produto);
        persist(adaptered);
    }

    public void editarProduto(Produto produto) {
        if (produto.getId().isPresent()) {
            var produtoDTO = findById(produto.getId().get());
            produtoDTO.alteraDados(produto);
            persist(produtoDTO);
        }

    }

    public boolean removerProduto(Long id) {
        return deleteById(id);
    }

    public Optional<Produto> buscarId(Long id) {
        var dto = findByIdOptional(id);
        return dto.map(AplicacaoMapper.INSTANCE::toProduto);
    }


}
