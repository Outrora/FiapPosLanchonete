package adapters.driven;

import adapters.driver.db.PostgresqlDbTestResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@QuarkusTest
@QuarkusTestResource(value = PostgresqlDbTestResource.class, restrictToAnnotatedClass = true)
public class RestClienteTest {
    @Test
    public void testCadastraeBuscarCliente() {
        // Primeiro, você precisa adicionar um cliente ao banco de dados
        String clienteJson = "{ \"cpf\": \"03696097195\", \"nome\": \"João\", \"email\": \"joao@example.com\" }";

        // Cadastrando o cliente
        given()
                .contentType("application/json")
                .body(clienteJson)
                .when()
                .post("/cliente")
                .then()
                .statusCode(201);

        // Agora, buscando o cliente
        given()
                .pathParam("cpf", "03696097195")
                .when()
                .get("/cliente/{cpf}")
                .then()
                .statusCode(200)
                .body("nome", is("João"));
    }

}
