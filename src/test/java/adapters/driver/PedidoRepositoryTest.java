package adapters.driver;

import adapters.driver.db.PostgresqlDbTestResource;
import br.com.lanchonete.adapters.driver.cozinha.FilaRepository;
import br.com.lanchonete.adapters.driver.pedido.PedidoRepository;
import br.com.lanchonete.adapters.driver.produto.ProdutoRepository;
import br.com.lanchonete.core.application.Pedido.PedidoRequest;
import br.com.lanchonete.core.domain.entities.Pedido;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import br.com.lanchonete.core.domain.enums.EstadoPedido;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.smallrye.common.constraint.Assert.assertNotNull;

@QuarkusTest
@QuarkusTestResource(value = PostgresqlDbTestResource.class, restrictToAnnotatedClass = true)
public class PedidoRepositoryTest {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    FilaRepository filaRepositoy;

    @Inject
    ProdutoRepository produtoRepository;

    @BeforeEach
    @Transactional
    public void limparBanco() {
        pedidoRepository.deleteAll(); // Assumindo que você tem esse método no repositório
    }

    @Test
    @Transactional
    public void testInserirPedido() {

        Produto produto = new Produto("teste", "teste", BigDecimal.ONE, Categoria.lanche, Optional.empty());
        Produto produto2 = new Produto("teste", "teste", BigDecimal.ONE, Categoria.lanche, Optional.empty());
        List<Pair<Integer, Produto>> produtosMock = new ArrayList<>();
        produtosMock.add(new Pair<>(1, produto));
        produtosMock.add(new Pair<>(2, produto2));

        produtosMock.forEach(produtoMock -> {
            produtoRepository.cadastrarProduto(produtoMock.b);
        });

        var fila = filaRepositoy.criarOuPegarFilaHoje();


        Pedido pedido = new Pedido(LocalDateTime.now(), Optional.empty(), produtosMock, EstadoPedido.recebido, BigDecimal.ZERO);
        pedido.calcularValorTotal();


        List<PedidoRequest.produtoQuantidade> produtos = List.of(
                new PedidoRequest.produtoQuantidade(1, 1L), // Exemplo de produto
                new PedidoRequest.produtoQuantidade(2, 2L)
        );

        UUID idPedido = pedidoRepository.inserirPedido(pedido, fila.id(), produtos);
        assertNotNull(idPedido);
    }
}