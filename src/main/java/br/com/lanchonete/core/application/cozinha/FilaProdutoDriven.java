package br.com.lanchonete.core.application.cozinha;

import br.com.lanchonete.core.domain.cozinha.FilaPedidoService;
import br.com.lanchonete.core.domain.entities.FilaPedidos;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class FilaProdutoDriven {

    @Inject
    FilaPedidoService filaPedidoService;

    public FilaPedidos pegarFilaComPedidos() {
        return filaPedidoService.pegarFilaComPedidos();
    }
}
