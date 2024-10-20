package domain.cliente;

import br.com.lanchonete.core.application.Cliente.ClientePortDriver;
import br.com.lanchonete.core.domain.cliente.validacoes.VerficaEmailExistente;
import br.com.lanchonete.core.domain.entities.Cliente;
import br.com.lanchonete.core.domain.exception.ErroValidacao;
import io.quarkus.test.InjectMock;
import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusComponentTest
public class VerificarEmail {

    @Inject
    VerficaEmailExistente verificaEmailExistente;

    @InjectMock
    ClientePortDriver clientePortDriver;


    @Test
    public void testValidarEmailExistente() {
        Cliente cliente = new Cliente("teste", "teste", "12345678909");

        // Simula que o e-mail existe
        when(clientePortDriver.pegarEmail(cliente.getEmail())).thenReturn(Optional.of(cliente));

        ErroValidacao exception = assertThrows(ErroValidacao.class, () -> {
            verificaEmailExistente.validar(cliente);
        });

        assertEquals("Email já cadastrado", exception.getMessage());
    }

    @Test
    public void testValidarEmailNaoExistente() {
        Cliente cliente = new Cliente("teste", "teste", "12345678909");

        // Simula que o e-mail não existe
        when(clientePortDriver.pegarEmail(cliente.getEmail())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            verificaEmailExistente.validar(cliente);
        });

        // Verifica se o método foi chamado
        verify(clientePortDriver).pegarEmail(cliente.getEmail());
    }
}
