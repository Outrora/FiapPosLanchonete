package adapters.driver;

import adapters.driver.db.PostgresqlDbTestResource;
import br.com.lanchonete.adapters.driver.cozinha.FilaRepository;
import br.com.lanchonete.core.domain.entities.FilaPedidos;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(value = PostgresqlDbTestResource.class, restrictToAnnotatedClass = true)
public class FilaRepositoryTest {


    @Inject
    FilaRepository filaRepository;

    @PersistenceContext
    EntityManager em;


    @Inject
    PedidoRepositoryTest pedidoRepositoryTest;

    @BeforeEach
    @Transactional
    public void limparBanco() {
        em.createNativeQuery("DELETE FROM pedido_produto").executeUpdate();
        em.createNativeQuery("DELETE FROM pedido").executeUpdate();
        em.createNativeQuery("DELETE FROM fila").executeUpdate();
    }

    @Test
    @Transactional
    public void testCriarOuPegarFilaHoje_CriaNovaFila() {
        FilaPedidos fila = filaRepository.criarOuPegarFilaHoje();

        assertNotNull(fila);
        assertEquals(LocalDate.now(), fila.dia());
        assertNotNull(fila.id());
        assertTrue(fila.listaPedidos().isEmpty());
    }

    @Test
    @Transactional
    public void testPegarFilaComPedidos_Fila() {
        pedidoRepositoryTest.testInserirPedido();
        FilaPedidos fila = filaRepository.pegarFilaComPedidos();

        assertNotNull(fila);
        assertEquals(LocalDate.now(), fila.dia());
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
