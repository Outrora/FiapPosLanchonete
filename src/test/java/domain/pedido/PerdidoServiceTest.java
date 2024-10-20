package domain.pedido;

import br.com.lanchonete.core.application.Pedido.PedidoPortDriver;
import br.com.lanchonete.core.application.Pedido.PedidoRequest;
import br.com.lanchonete.core.domain.cliente.ServiceCliente;
import br.com.lanchonete.core.domain.cozinha.FilaPedidoService;
import br.com.lanchonete.core.domain.entities.Cliente;
import br.com.lanchonete.core.domain.entities.FilaPedidos;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import br.com.lanchonete.core.domain.exception.ErroValidacao;
import br.com.lanchonete.core.domain.pedido.PedidoService;
import br.com.lanchonete.core.domain.pedido.Validadores.ValidarPedido;
import br.com.lanchonete.core.domain.produto.ServiceProduto;
import io.quarkus.test.InjectMock;
import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusComponentTest
public class PerdidoServiceTest {
    @Inject
    PedidoService pedidoService;

    @InjectMock
    List<ValidarPedido> validadores;

    @InjectMock
    ServiceProduto serviceProduto;

    @InjectMock
    ServiceCliente clienteService;

    @InjectMock
    FilaPedidoService filaPedidoService;

    @InjectMock
    PedidoPortDriver driver;


    @Test
    public void testCadastrarPedido() {
        // Mocking other services
        Cliente mockCliente = mock(Cliente.class);
        when(clienteService.pegarID(1L)).thenReturn(Optional.ofNullable(mockCliente));

        Set<Long> idsProdutos = Set.of(1L);
        List<Produto> mockProdutos = List.of(new Produto("Deste", "Produto 1", BigDecimal.valueOf(100.0), Categoria.lanche, Optional.of(1L)));
        when(serviceProduto.pegarPelosId(idsProdutos)).thenReturn(mockProdutos);

        // Mocking fila e driver
        FilaPedidos mockFila = new FilaPedidos(new ArrayList<>(), LocalDate.now(), 1L);
        when(filaPedidoService.criarFilaouPegarAtual()).thenReturn(mockFila);
        when(driver.salvar(any(), any(), any())).thenReturn(UUID.randomUUID());

        // Executando o método
        PedidoRequest request = new PedidoRequest();
        PedidoRequest.produtoQuantidade produtoQuantidade = new PedidoRequest.produtoQuantidade(1, 1L);
        request.setId_cliente(1L);
        request.setProdutos(List.of(produtoQuantidade));
        UUID resultado = pedidoService.cadastrarPedido(request);

        // Verificações
        assertNotNull(resultado);
        verify(clienteService).pegarID(1L);
        verify(serviceProduto).pegarPelosId(idsProdutos);
        verify(driver).salvar(any(), eq(mockFila), any());
    }

    @Test
    public void testVerificarIds_ProdutosNaoEncontrados() {
        Set<Long> idsProdutos = new HashSet<>(Set.of(1L, 2L));
        List<Produto> mockProdutos = List.of(new Produto("Deste", "Produto 1", BigDecimal.valueOf(100.0), Categoria.lanche, Optional.of(1L)));

        Exception exception = assertThrows(ErroValidacao.class, () -> {
            pedidoService.verificarIds(idsProdutos, mockProdutos);
        });

        assertEquals("Produtos não encontrados: Ids: 2", exception.getMessage());
    }
}
