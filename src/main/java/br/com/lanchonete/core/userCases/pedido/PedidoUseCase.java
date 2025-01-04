package br.com.lanchonete.core.userCases.pedido;

import br.com.lanchonete.core.adapters.Pedido.IPedidoGateway;
import br.com.lanchonete.core.adapters.Pedido.PedidoRequest;
import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.userCases.cliente.BuscarClienteUseCase;
import br.com.lanchonete.core.userCases.cozinha.ListaPedidoUseCase;
import br.com.lanchonete.core.userCases.exception.ErroValidacao;
import br.com.lanchonete.core.userCases.pagamento.CriarPedidoPagamentoUseCase;
import br.com.lanchonete.core.userCases.pedido.Validadores.ValidarPedido;
import br.com.lanchonete.core.userCases.produto.BuscarProdutoUseCase;
import io.quarkus.arc.All;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScoped
public class PedidoUseCase {

    @Inject
    @All
    List<ValidarPedido> validadores;

    @Inject
    BuscarClienteUseCase buscarClienteUseCase;

    @Inject
    BuscarProdutoUseCase busacarProdutoUseCase;

    @Inject
    ListaPedidoUseCase filaPedidoUseCase;

    @Inject
    CriarPedidoPagamentoUseCase criarPedidoPagamentoUseCase;

    @Inject
    IPedidoGateway gateway;

    public UUID cadastrarPedido(PedidoRequest request) {
        System.out.println(validadores);

        for (var validador : validadores) {
            validador.validar(request);
        }

        var cliente = buscarClienteUseCase.pegarID(request.getId_cliente());

        var idsProdutos = request.getProdutos()
                .stream()
                .map(PedidoRequest.produtoQuantidade::id)
                .collect(Collectors.toSet());
        var produtosEncontrados = busacarProdutoUseCase.pegarPelosId(idsProdutos);

        verificarIds(idsProdutos, produtosEncontrados);
        var lista = produtosEncontrados
                .stream()
                .map(produto -> {
                    var quandidade = request.getProdutos()
                            .stream()
                            .filter(produtoQuantidade -> produtoQuantidade.id().equals(produto.getId().get()))
                            .findFirst()
                            .orElseThrow();
                    return new Pair<>(quandidade.quandidade(), produto);
                })
                .toList();

        var pedidos = Pedido.ofNovo(cliente, lista);
        pedidos.calcularValorTotal();

        var filaAtual = filaPedidoUseCase.criarFilaouPegarAtual();

        var id = gateway.salvar(pedidos, filaAtual, request.getProdutos());

        criarPedidoPagamentoUseCase.criarPagamento(id);

        return id;

    }

    public void verificarIds(Set<Long> idsProdutos, List<Produto> produtosEncontrados) {
        if (idsProdutos.size() != produtosEncontrados.size()) {
            var produtosEncontradosIds = produtosEncontrados
                    .stream()
                    .map(produto -> produto.getId().orElseThrow())
                    .collect(Collectors.toSet());

            var produtosNaoEncontrados = idsProdutos.stream()
                    .filter(id -> !produtosEncontradosIds.contains(id))
                    .collect(Collectors.toSet());
            StringBuilder mensagemErro = new StringBuilder("Produtos não encontrados: Ids: ");
            produtosNaoEncontrados.forEach(mensagemErro::append);
            throw new ErroValidacao(mensagemErro.toString());
        }
    }

}
