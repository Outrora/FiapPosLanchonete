package adapters.driven;

import adapters.driver.db.PostgresqlDbTestResource;
import br.com.lanchonete.core.application.Pedido.PedidoPortDriven;
import br.com.lanchonete.core.application.Pedido.PedidoRequest;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
@QuarkusTestResource(value = PostgresqlDbTestResource.class, restrictToAnnotatedClass = true)
public class RestPedidoTest {

    @InjectMock
    PedidoPortDriven driven;

    @Test
    public void testCadastrarProduto() {

        var pedido = new PedidoRequest();
        var produto = new PedidoRequest.produtoQuantidade(1, 1L);
        pedido.setProdutos(List.of(produto));
        pedido.setId_cliente(1L);

        var random = UUID.randomUUID();
        when(driven.cadatrarPedido(any())).thenReturn(random);
        // Supondo que você tenha um produto na categoria "lanche"
        given()
                .contentType("application/json")
                .body(pedido)
                .when()
                .post("/pedido")
                .then()
                .statusCode(200)
                .body(notNullValue()); // Verifica se a resposta não é nula
    }
}
