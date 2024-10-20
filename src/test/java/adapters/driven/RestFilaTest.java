package adapters.driven;

import adapters.driver.db.PostgresqlDbTestResource;
import br.com.lanchonete.core.application.cozinha.FilaProdutoDriven;
import br.com.lanchonete.core.domain.entities.FilaPedidos;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@QuarkusTest
@QuarkusTestResource(value = PostgresqlDbTestResource.class, restrictToAnnotatedClass = true)
public class RestFilaTest {


    @InjectMock
    FilaProdutoDriven driven;

    @Test
    public void testPegarProdutoCategoria() {

        FilaPedidos filaPedidos = new FilaPedidos(new ArrayList<>(), LocalDate.now(), 1L);
        when(driven.pegarFilaComPedidos()).thenReturn(filaPedidos);
        // Supondo que você tenha um produto na categoria "lanche"
        given()
                .when()
                .get("/cozinha")
                .then()
                .statusCode(200)
                .body(notNullValue()); // Verifica se a resposta não é nula
    }


}
