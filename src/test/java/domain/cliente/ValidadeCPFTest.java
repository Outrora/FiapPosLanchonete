package domain.cliente;

import br.com.lanchonete.core.domain.cliente.validacoes.ValidadeCPF;
import br.com.lanchonete.core.domain.entities.Cliente;
import br.com.lanchonete.core.domain.exception.ErroValidacao;
import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusComponentTest
public class ValidadeCPFTest {

    @Inject
    ValidadeCPF validadorCliente;

    @Test
    public void testValidarCpfValido() {
        Cliente cliente = new Cliente("teste", "teste", "12345678909");

        assertDoesNotThrow(() -> {
            validadorCliente.validar(cliente);
        });
    }

    @Test
    public void testValidarCpfInvalido() {
        Cliente cliente = new Cliente("teste", "teste", "12345678900");

        ErroValidacao exception = assertThrows(ErroValidacao.class, () -> {
            validadorCliente.validar(cliente);
        });

        assertEquals("cpf Invalido", exception.getMessage());
    }

    @Test
    public void testValidarCpfComSequenciaInvalida() {
        Cliente cliente = new Cliente("teste", "teste", "11111111111");

        ErroValidacao exception = assertThrows(ErroValidacao.class, () -> {
            validadorCliente.validar(cliente);
        });

        assertEquals("cpf Invalido", exception.getMessage());
    }

    @Test
    public void testValidarCpfComSequenciaInvalida2() {
        Cliente cliente = new Cliente("teste", "teste", "77777777777");

        ErroValidacao exception = assertThrows(ErroValidacao.class, () -> {
            validadorCliente.validar(cliente);
        });

        assertEquals("cpf Invalido", exception.getMessage());
    }

    @Test
    public void testValidarCpfComTamanhoInvalido() {
        Cliente cliente = new Cliente("teste", "teste", "111");

        ErroValidacao exception = assertThrows(ErroValidacao.class, () -> {
            validadorCliente.validar(cliente);
        });

        assertEquals("cpf Invalido", exception.getMessage());
    }
}
