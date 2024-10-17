package adapters.driver;

import adapters.driver.db.PostgresqlDbTestResource;
import br.com.lanchonete.adapters.driver.cliente.ClienteRepository;
import br.com.lanchonete.core.domain.entities.Cliente;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static io.smallrye.common.constraint.Assert.assertFalse;
import static io.smallrye.common.constraint.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@QuarkusTestResource(value = PostgresqlDbTestResource.class, restrictToAnnotatedClass = true)
public class ClienteRepositoryTest {

    @Inject
    ClienteRepository clienteRepository;

    @BeforeEach
    @Transactional
    public void limparBanco() {
        clienteRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente("João", "joao@example.com", "12345678901");

        clienteRepository.cadastrarCliente(cliente);

        Optional<Cliente> clienteBuscado = clienteRepository.buscarCPF("12345678901");

        assertTrue(clienteBuscado.isPresent());
        assertEquals("João", clienteBuscado.orElseThrow().getNome());
    }

    @Test
    @Transactional
    public void testBuscarEmail() {
        Cliente cliente = new Cliente("Maria", "maria@example.com", "98765432100");
        clienteRepository.cadastrarCliente(cliente);

        Optional<Cliente> clienteBuscado = clienteRepository.buscarEmail("maria@example.com");

        assertTrue(clienteBuscado.isPresent());
        assertEquals("Maria", clienteBuscado.orElseThrow().getNome());
    }

    @Test
    @Transactional
    public void testBuscarID() {
        Cliente cliente = new Cliente("José", "11122233344", "jose@example.com");
        clienteRepository.cadastrarCliente(cliente);

        Optional<Cliente> clienteBuscado = clienteRepository.buscarID(1L); // Ajuste o ID conforme necessário

        assertTrue(clienteBuscado.isPresent());
        assertEquals("José", clienteBuscado.orElseThrow().getNome());
    }

    @Test
    @Transactional
    public void testBuscarCPF_NaoEncontrado() {
        Optional<Cliente> clienteBuscado = clienteRepository.buscarCPF("00000000000");
        assertFalse(clienteBuscado.isPresent());
    }
}
