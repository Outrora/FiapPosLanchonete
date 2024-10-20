package adapters.driven;

import adapters.driver.db.PostgresqlDbTestResource;
import br.com.lanchonete.core.application.Produto.ProdutoAdapter;
import br.com.lanchonete.core.application.Produto.ProdutoPortDriven;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
@QuarkusTestResource(value = PostgresqlDbTestResource.class, restrictToAnnotatedClass = true)
public class RestProdutoTest {


    @InjectMock
    ProdutoPortDriven driven;

    @Test
    public void testPegarProdutoCategoria() {

        when(driven.pegarCategoria(any())).thenReturn(Set.of(
                new Produto("teste", "teste", BigDecimal.ONE, Categoria.acompanhamento, Optional.of(1L))
        ));
        // Supondo que você tenha um produto na categoria "lanche"
        given()
                .pathParam("categoria", "acompanhamento")
                .when()
                .get("/produto/categoria/{categoria}")
                .then()
                .statusCode(200)
                .body(notNullValue()); // Verifica se a resposta não é nula
    }

    @Test
    public void testDeletarProduto() {
        // Primeiro, insira um produto e obtenha seu ID
        Long produtoId = 1L; // Substitua pelo ID correto

        when(driven.excluiProduto(any())).thenReturn(true);
        given()
                .pathParam("id", produtoId)
                .when()
                .delete("/produto/{id}")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    public void testDeletarProdutoErro() {
        // Primeiro, insira um produto e obtenha seu ID
        Long produtoId = 1L; // Substitua pelo ID correto

        when(driven.excluiProduto(any())).thenReturn(false);
        given()
                .pathParam("id", produtoId)
                .when()
                .delete("/produto/{id}")
                .then()
                .statusCode(400)
                .body(notNullValue());
    }

    @Test
    public void testAlterarProduto() {
        Long produtoId = 1L; // Substitua pelo ID correto
        ProdutoAdapter adapter = new ProdutoAdapter("teste", "teste", BigDecimal.ONE, Categoria.acompanhamento);


        given()
                .contentType("application/json")
                .pathParam("id", produtoId)
                .body(adapter)
                .when()
                .put("/produto/{id}")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    public void testInserirProduto() {
        ProdutoAdapter adapter = new ProdutoAdapter("teste", "teste", BigDecimal.ONE, Categoria.acompanhamento);


        given()
                .contentType("application/json")
                .body(adapter)
                .when()
                .post("/produto")
                .then()
                .statusCode(201)
                .body(notNullValue());
    }
}
