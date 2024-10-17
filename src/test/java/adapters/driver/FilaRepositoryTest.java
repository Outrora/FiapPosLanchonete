package adapters.driver;

import adapters.driver.db.PostgresqlDbTestResource;
import br.com.lanchonete.adapters.driver.cozinha.FilaRepository;
import br.com.lanchonete.adapters.driver.pedido.PedidoRepository;
import br.com.lanchonete.adapters.driver.produto.ProdutoRepository;
import br.com.lanchonete.core.domain.entities.FilaPedidos;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(value = PostgresqlDbTestResource.class, restrictToAnnotatedClass = true)
public class FilaRepositoryTest {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    FilaRepository filaRepositoy;

    @Inject
    ProdutoRepository produtoRepository;


    @Inject
    FilaRepository filaRepository;

    @BeforeEach
    @Transactional
    public void limparBanco() {
        filaRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCriarOuPegarFilaHoje_CriaNovaFila() {
        // A data atual deve ser usada
        FilaPedidos fila = filaRepository.criarOuPegarFilaHoje();

        assertNotNull(fila);
        assertEquals(LocalDate.now(), fila.dia());
        assertNotNull(fila.id());
        assertTrue(fila.listaPedidos().isEmpty());
    }


    @Test
    @Transactional
    public void testPegarFilaComPedidos_FilaInexistente() {

        FilaPedidos fila = filaRepository.pegarFilaComPedidos();

        assertNotNull(fila);
        assertEquals(LocalDate.now(), fila.dia());
        assertTrue(fila.listaPedidos().isEmpty());
    }
}
