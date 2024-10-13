package br.com.lanchonete.core.application.cozinha;

import br.com.lanchonete.core.domain.cozinha.FilaPedidoService;
import br.com.lanchonete.core.domain.entities.FilaPedidos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FilaProdutoDriven {

    @Inject
    FilaPedidoService filaPedidoService;

    public FilaPedidos pegarFilaComPedidos() {
        return filaPedidoService.pegarFilaComPedidos();
    }
}
