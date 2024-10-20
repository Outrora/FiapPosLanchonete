package domain.cozinha;

import br.com.lanchonete.core.application.cozinha.FilaProdutoDriver;
import br.com.lanchonete.core.domain.cozinha.FilaPedidoService;
import br.com.lanchonete.core.domain.entities.FilaPedidos;
import br.com.lanchonete.core.domain.entities.Pedido;
import io.quarkus.test.InjectMock;
import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusComponentTest
public class FilaTest {

    @InjectMock
    FilaProdutoDriver driver; // Mock do FilaProdutoDriver

    @Inject
    FilaPedidoService filaPedidoService; // Classe que estamos testando


    @Test
    public void testCriarFilaouPegarAtual() {
        List<Pedido> lista = new ArrayList<>();
        FilaPedidos filaPedidosMock = new FilaPedidos(lista, LocalDate.now(), 1L);
        when(driver.criarFilaOuPegarAtual()).thenReturn(filaPedidosMock);


        FilaPedidos resultado = filaPedidoService.criarFilaouPegarAtual();


        verify(driver).criarFilaOuPegarAtual();

        assertEquals(filaPedidosMock, resultado);
    }

    @Test
    public void testPegarFilaComPedidos() {
        List<Pedido> lista = new ArrayList<>();
        FilaPedidos filaPedidosMock = new FilaPedidos(lista, LocalDate.now(), 1L);
        when(driver.pegarFilaComPedidos()).thenReturn(filaPedidosMock);
        FilaPedidos resultado = filaPedidoService.pegarFilaComPedidos();
        verify(driver).pegarFilaComPedidos();
        assertEquals(filaPedidosMock, resultado);
    }
}
