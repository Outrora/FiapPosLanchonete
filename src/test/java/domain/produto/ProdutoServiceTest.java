package domain.produto;

import br.com.lanchonete.core.application.Produto.ProdutoPortDriver;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import br.com.lanchonete.core.domain.exception.ResultadaoVazioErro;
import br.com.lanchonete.core.domain.produto.ServiceProduto;
import io.quarkus.test.InjectMock;
import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusComponentTest
public class ProdutoServiceTest {

    @Inject
    ServiceProduto serviceProduto;

    @InjectMock
    ProdutoPortDriver driver;


    @Test
    public void testSalvarDados() {
        Produto produto = new Produto("teste", "teste", BigDecimal.valueOf(1), Categoria.lanche, Optional.of(1L));

        // Execute o método
        serviceProduto.salvarDados(produto);

        // Verifique se o método salvar do driver foi chamado
        verify(driver).salvar(produto);
    }

    @Test
    public void testBuscarPelaCategoria() {
        Set<Produto> produtosEsperados = new HashSet<>();

        when(driver.buscarPorCategoria(Categoria.lanche)).thenReturn(produtosEsperados);

        Set<Produto> resultado = serviceProduto.buscarPelaCategoria(Categoria.lanche);

        assertEquals(produtosEsperados, resultado);
        verify(driver).buscarPorCategoria(Categoria.lanche);
    }

    @Test
    public void testExcluir() {
        Long id = 1L;
        when(driver.excluir(id)).thenReturn(true);

        boolean resultado = serviceProduto.excluir(id);

        assertTrue(resultado);
        verify(driver).excluir(id);
    }

    @Test
    public void testPegarPelosId() {
        Set<Long> ids = Set.of(1L, 2L);
        Produto produto = new Produto("teste", "teste", BigDecimal.valueOf(1), Categoria.lanche, Optional.of(1L));
        List<Produto> produtosEsperados = List.of(produto);

        when(driver.pegarId(ids)).thenReturn(produtosEsperados);

        List<Produto> resultado = serviceProduto.pegarPelosId(ids);

        assertEquals(produtosEsperados, resultado);
        verify(driver).pegarId(ids);
    }

    @Test
    public void testEditarDadosProdutoExistente() {
        Produto produto = new Produto("teste", "teste", BigDecimal.valueOf(1), Categoria.lanche, Optional.of(1L));


        when(driver.pegarId(produto.getId().get())).thenReturn(Optional.of(produto));

        // Execute o método
        serviceProduto.editarDados(produto);

        // Verifique se o método alterar foi chamado
        verify(driver).alterar(produto);
    }

    @Test
    public void testEditarDadosProdutoNaoExistente() {
        Produto produto = new Produto("teste", "teste", BigDecimal.valueOf(1), Categoria.lanche, Optional.of(1L));

        when(driver.pegarId(produto.getId().get())).thenReturn(Optional.empty());

        // Verifique se a exceção é lançada
        assertThrows(ResultadaoVazioErro.class, () -> serviceProduto.editarDados(produto));
    }

    @Test
    public void testEditarDadosProdutoSemId() {
        Produto produto = new Produto("teste", "teste", BigDecimal.valueOf(1), Categoria.lanche, Optional.empty());

        // Verifique se a exceção é lançada
        assertThrows(ResultadaoVazioErro.class, () -> serviceProduto.editarDados(produto));
    }
}
