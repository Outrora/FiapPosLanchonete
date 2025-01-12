package br.com.lanchonete.core.adapters.cozinha;

import java.util.List;

import br.com.lanchonete.core.adapters.base.BaseController;
import br.com.lanchonete.core.entities.FilaPedidos;
import br.com.lanchonete.core.entities.Pedido;
import br.com.lanchonete.core.userCases.cozinha.FilaUseCase;
import br.com.lanchonete.core.userCases.cozinha.ListaPedidoUseCase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class FilaProdutoController extends BaseController {

    @Inject
    ListaPedidoUseCase filaPedidoService;

    @Inject
    FilaUseCase filaUseCase;

    public FilaPedidos pegarFilaComPedidos() {
        LOG.info("Pegando fila com pedidos");
        return filaPedidoService.criarFilaouPegarAtual();
    }

    public List<Pedido> pegarFilaComPedidosEmPreparo() {
        LOG.info("Pegando fila com pedidos para Preparação");
        return filaUseCase.pegarFila();
    }
}
