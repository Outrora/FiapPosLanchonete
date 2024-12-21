package br.com.lanchonete.core.adapters.cozinha;

import br.com.lanchonete.core.entities.FilaPedidos;
import br.com.lanchonete.core.userCases.cozinha.FilaPedidoUseCase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class FilaProdutoController {

    @Inject
    FilaPedidoUseCase filaPedidoService;

    public FilaPedidos pegarFilaComPedidos() {
        return filaPedidoService.pegarFilaComPedidos();
    }
}
