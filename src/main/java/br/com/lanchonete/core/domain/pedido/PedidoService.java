package br.com.lanchonete.core.domain.pedido;

import br.com.lanchonete.core.application.Pedido.PedidoPortDriver;
import br.com.lanchonete.core.application.Pedido.PedidoRequest;
import br.com.lanchonete.core.domain.cliente.ServiceCliente;
import br.com.lanchonete.core.domain.cozinha.FilaPedidoService;
import br.com.lanchonete.core.domain.entities.Pedido;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.exception.ErroValidacao;
import br.com.lanchonete.core.domain.pedido.Validadores.ValidarPedido;
import br.com.lanchonete.core.domain.produto.ServiceProduto;
import io.quarkus.arc.All;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoService {

    @Inject
    @All
    List<ValidarPedido> validadores;

    @Inject
    ServiceProduto serviceProduto;

    @Inject
    ServiceCliente clienteService;

    @Inject
    FilaPedidoService filaPedidoService;

    @Inject
    PedidoPortDriver driver;


    public UUID cadastrarPedido(PedidoRequest request) {

        for (var validador : validadores) {
            validador.validar(request);
        }

        var cliente = clienteService.pegarID(request.getId_cliente());

        var idsProdutos = request.getProdutos()
                .stream()
                .map(PedidoRequest.produtoQuantidade::id)
                .collect(Collectors.toSet());
        var produtosEncontrados = serviceProduto.pegarPelosId(idsProdutos);

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

        var filaAtual = filaPedidoService.criarFilaouPegarAtual();

        return driver.salvar(pedidos, filaAtual, request.getProdutos());


    }


    public void verificarIds(Set<Long> idsProdutos, List<Produto> produtosEncontrados) {
        if (idsProdutos.size() != produtosEncontrados.size()) {
            var produtosEncontradosIds = produtosEncontrados
                    .stream()
                    .map(produto -> produto.getId().get())
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
