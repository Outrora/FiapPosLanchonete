package adapters.driver;

import adapters.driver.db.PostgresqlDbTestResource;
import br.com.lanchonete.adapters.driver.produto.ProdutoDTO;
import br.com.lanchonete.adapters.driver.produto.ProdutoRepository;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static io.smallrye.common.constraint.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@QuarkusTest
@QuarkusTestResource(value = PostgresqlDbTestResource.class, restrictToAnnotatedClass = true)
public class ProdutoRepositoryTest {

    @Inject
    ProdutoRepository produtoRepository;

    @BeforeEach
    @Transactional
    public void limparBanco() {
        produtoRepository.deleteAll();
        produtoRepository.resetSequence();
    }

    @Test
    @Transactional(Transactional.TxType.REQUIRED)
    public void testCadastrarProduto() {
        Produto produto = new Produto("teste", "teste", BigDecimal.ONE, Categoria.lanche, Optional.empty());
        // Configure o produto conforme necessário

        produtoRepository.cadastrarProduto(produto);

        List<ProdutoDTO> produtos = produtoRepository.buscarPorCatergoria(produto.getCategoria());

        assertEquals(1, produtos.size());
    }

    @Test
    @Transactional(Transactional.TxType.REQUIRED)
    public void testEditarProduto() {
        Produto produto = new Produto("teste", "teste", BigDecimal.ONE, Categoria.lanche, Optional.empty());
        produtoRepository.cadastrarProduto(produto);
        produtoRepository.findAll().list().stream().findFirst().ifPresent(produtos -> System.out.println(produtos.getId()));


        Produto produtoAterado = new Produto("Produto Editado", "teste", BigDecimal.valueOf(150.0), Categoria.lanche, Optional.of(1L));
        produtoRepository.editarProduto(produtoAterado);

        ProdutoDTO produtoDTO = produtoRepository.findById(1L);
        assertEquals("Produto Editado", produtoDTO.getNome());
        assertEquals(BigDecimal.valueOf(150.0), produtoDTO.getPreco());
    }

    @Test
    @Transactional(Transactional.TxType.REQUIRED)
    public void testRemoverProduto() {
        Produto produto = new Produto("teste", "teste", BigDecimal.ONE, Categoria.lanche, Optional.of(1L));

        produtoRepository.cadastrarProduto(produto);

        boolean resultado = produtoRepository.removerProduto(produto.getId().get());

        assertTrue(resultado);
        assertFalse(produtoRepository.findByIdOptional(produto.getId().get()).isPresent());
    }

    @Test
    @Transactional(Transactional.TxType.REQUIRED)
    public void testBuscarId() {
        Produto produto = new Produto("Produto para Buscar", "teste", BigDecimal.ONE, Categoria.lanche, Optional.of(1L));
        produtoRepository.cadastrarProduto(produto);

        Optional<Produto> resultado = produtoRepository.buscarId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Produto para Buscar", resultado.orElseThrow().getNome());
    }

    @Test
    @Transactional(Transactional.TxType.REQUIRED)
    public void testBuscarListaId() {
        Produto produto = new Produto("Produto para Buscar", "teste", BigDecimal.ONE, Categoria.lanche, Optional.of(1L));
        Produto produto2 = new Produto("Produto para Buscar", "teste", BigDecimal.ONE, Categoria.lanche, Optional.of(1L));

        produtoRepository.cadastrarProduto(produto);
        produtoRepository.cadastrarProduto(produto2);

        List<Produto> resultado = produtoRepository.buscarId(Set.of(1L, 2L));

        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
    }

    @Test
    @Transactional(Transactional.TxType.REQUIRED)
    public void testBuscarPorCategoria() {
        Produto produto = new Produto("teste", "teste", BigDecimal.ONE, Categoria.lanche, Optional.empty());
        produtoRepository.cadastrarProduto(produto);


        var produtos = produtoRepository.buscarPorCatergoria(Categoria.lanche);
        assertNotNull(produtos);
    }
}
