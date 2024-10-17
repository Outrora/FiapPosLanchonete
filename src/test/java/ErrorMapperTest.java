import br.com.lanchonete.adapters.ErrorMapper;
import br.com.lanchonete.core.domain.exception.ErroValidacao;
import br.com.lanchonete.core.domain.exception.ResultadoVazioErro;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ErrorMapperTest {

    @InjectMocks
    ErrorMapper errorMapper;

    @Mock
    ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ObjectNode objectNodeMock = mock(ObjectNode.class);
        when(objectMapper.createObjectNode()).thenReturn(objectNodeMock);
    }

    @Test
    public void testToResponseResultadoVazioErro() {
        Exception exception = new ResultadoVazioErro("No results found");
        ObjectNode exceptionJson = new ObjectMapper().createObjectNode();
        exceptionJson.put("exceptionType", exception.getClass().getName());
        exceptionJson.put("code", 404);
        exceptionJson.put("error", exception.getMessage());

        // Mockando o comportamento do ObjectMapper
        when(objectMapper.createObjectNode()).thenReturn(exceptionJson);

        Response response = errorMapper.toResponse(exception);

        assertEquals(404, response.getStatus());
        assertEquals(exceptionJson.toString(), response.getEntity().toString());
    }

    @Test
    public void testToResponseErroValidacao() {
        Exception exception = new ErroValidacao("Validation error");
        ObjectNode exceptionJson = new ObjectMapper().createObjectNode();
        exceptionJson.put("exceptionType", exception.getClass().getName());
        exceptionJson.put("code", 400);
        exceptionJson.put("error", exception.getMessage());

        when(objectMapper.createObjectNode()).thenReturn(exceptionJson);

        Response response = errorMapper.toResponse(exception);

        assertEquals(400, response.getStatus());
        assertEquals(exceptionJson.toString(), response.getEntity().toString());
    }

    @Test
    public void testToResponseGenericException() {
        Exception exception = new Exception("Generic error");
        ObjectNode exceptionJson = new ObjectMapper().createObjectNode();
        exceptionJson.put("exceptionType", exception.getClass().getName());
        exceptionJson.put("code", 500);
        exceptionJson.put("error", exception.getMessage());

        when(objectMapper.createObjectNode()).thenReturn(exceptionJson);

        Response response = errorMapper.toResponse(exception);

        assertEquals(500, response.getStatus());
        assertEquals(exceptionJson.toString(), response.getEntity().toString());
    }
}
