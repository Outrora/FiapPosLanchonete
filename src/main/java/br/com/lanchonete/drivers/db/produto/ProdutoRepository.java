package br.com.lanchonete.drivers.db.produto;

import br.com.lanchonete.core.adapters.Produto.ProdutoBanco;
import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.entities.enums.Categoria;
import br.com.lanchonete.drivers.AplicacaoMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<ProdutoDTO>, ProdutoBanco {


    public List<ProdutoDTO> buscarPorCatergoria(Categoria categoria) {
        return find("categoria", categoria).list();
    }

    //Somente para Teste
    public void resetSequence() {
        getEntityManager().createNativeQuery("ALTER SEQUENCE produto_id_seq RESTART WITH 1").executeUpdate();
    }


    public void cadastrarProduto(Produto produto) {
        var adaptered = new ProdutoDTO();
        adaptered.alteraDados(produto);
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

    public List<Produto> buscarId(Set<Long> id) {
        return find("id IN ?1", id)
                .stream()
                .map(AplicacaoMapper.INSTANCE::toProduto)
                .toList();
    }


}
