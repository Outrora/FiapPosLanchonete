package domain.cliente;

import br.com.lanchonete.core.application.Cliente.ClientePortDriver;
import br.com.lanchonete.core.domain.cliente.validacoes.VerficaCPFExistente;
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
public class VerificaCPFExitenteTest {

    @Inject
    VerficaCPFExistente verificaCPFExistente;

    @InjectMock
    ClientePortDriver clientePortDriver;


    @Test
    public void testValidarCpfExistente() {
        Cliente cliente = new Cliente("teste", "teste", "12345678909");

        // Simula que o CPF existe
        when(clientePortDriver.pegarCPF(cliente.getCpf())).thenReturn(Optional.of(cliente));

        ErroValidacao exception = assertThrows(ErroValidacao.class, () -> {
            verificaCPFExistente.validar(cliente);
        });

        assertEquals("Usuario já cadastrado", exception.getMessage());
    }

    @Test
    public void testValidarCpfNaoExistente() {
        Cliente cliente = new Cliente("teste", "teste", "12345678909");

        // Simula que o CPF não existe
        when(clientePortDriver.pegarCPF(cliente.getCpf())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            verificaCPFExistente.validar(cliente);
        });


        verify(clientePortDriver).pegarCPF(cliente.getCpf());
    }
}
