package domain.pedido;

import br.com.lanchonete.core.application.Pedido.PedidoRequest;
import br.com.lanchonete.core.domain.exception.ErroValidacao;
import br.com.lanchonete.core.domain.pedido.Validadores.ValidadarProduto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ValidarProdutoTest {
    ValidadarProduto validadarProduto = new ValidadarProduto();

    @Test
    public void testValidarProdutosVazio() {
        PedidoRequest request = new PedidoRequest();
        request.setProdutos(List.of()); // Adiciona uma lista vazia de produtos

        ErroValidacao exception = assertThrows(ErroValidacao.class, () -> {
            validadarProduto.validar(request);
        });

        assertEquals("Produtos Vazio", exception.getMessage());
    }

    @Test
    public void testValidarIdRepetido() {
        PedidoRequest request = new PedidoRequest();
        request.setProdutos(List.of(new PedidoRequest.produtoQuantidade(1, 2L),
                new PedidoRequest.produtoQuantidade(1, 2L))); // IDs repetidos

        ErroValidacao exception = assertThrows(ErroValidacao.class, () -> {
            validadarProduto.validar(request);
        });

        assertEquals("ID repetido encontrado: 2", exception.getMessage());
    }

    @Test
    public void testValidarSucesso() {
        PedidoRequest request = new PedidoRequest();
        request.setProdutos(List.of(new PedidoRequest.produtoQuantidade(1, 2L),
                new PedidoRequest.produtoQuantidade(2, 3L))); // IDs únicos

        assertDoesNotThrow(() -> {
            validadarProduto.validar(request);
        });
    }
}
