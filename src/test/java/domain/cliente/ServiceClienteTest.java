package domain.cliente;

import br.com.lanchonete.core.application.Cliente.ClientePortDriver;
import br.com.lanchonete.core.domain.cliente.ServiceCliente;
import br.com.lanchonete.core.domain.cliente.validacoes.ValidacaoCliente;
import br.com.lanchonete.core.domain.entities.Cliente;
import br.com.lanchonete.core.domain.exception.ResultadaoVazioErro;
import io.quarkus.test.InjectMock;
import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusComponentTest
public class ServiceClienteTest {


    @InjectMock
    List<ValidacaoCliente> validadores;

    @InjectMock
    ClientePortDriver driver;

    @Inject
    ServiceCliente serviceCliente;


    @Test
    public void testSalvarDados_DeveValidarESalvarCliente() {
        Cliente cliente = new Cliente("paulo", "12312@gmail.com", "036945456"); // Preencha com dados de teste
        serviceCliente.salvarDados(cliente);
        verify(driver).salvar(cliente);
    }

    @Test
    public void testPegarID_ClienteEncontrado() {
        Long id = 1L;
        Cliente cliente = new Cliente("paulo", "12312@gmail.com", "036945456"); // Preencha com dados de teste
        when(driver.pegarId(id)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = serviceCliente.pegarID(id);

        assertTrue(resultado.isPresent());
        assertEquals(cliente, resultado.get());
    }

    @Test
    public void testPegarID_ClienteNaoEncontrado() {
        Long id = 1L;
        when(driver.pegarId(id)).thenReturn(Optional.empty());

        Optional<Cliente> resultado = serviceCliente.pegarID(id);

        assertFalse(resultado.isPresent());
    }

    @Test
    public void testPegarCPF_ClienteEncontrado() {
        String cpf = "12345678900";
        Cliente cliente = new Cliente("paulo", "12312@gmail.com", "12345678900"); // Preencha com dados de teste
        when(driver.pegarCPF(cpf)).thenReturn(Optional.of(cliente));

        Cliente resultado = serviceCliente.pegarCPF(cpf);

        assertNotNull(resultado);
        assertEquals(cliente, resultado);
    }

    @Test
    public void testPegarCPF_ClienteNaoEncontrado() {
        String cpf = "12345678900";
        when(driver.pegarCPF(cpf)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(ResultadaoVazioErro.class, () -> {
            serviceCliente.pegarCPF(cpf);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
    }
}
