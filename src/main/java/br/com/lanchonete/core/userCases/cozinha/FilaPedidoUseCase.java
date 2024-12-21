package br.com.lanchonete.core.userCases.cozinha;

import br.com.lanchonete.core.adapters.cozinha.IFilaGateway;
import br.com.lanchonete.core.entities.FilaPedidos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FilaPedidoUseCase {

    @Inject
    IFilaGateway gateway;

    public FilaPedidos criarFilaouPegarAtual() {
        return gateway.criarOuPegarFilaHoje();
    }

    public FilaPedidos pegarFilaComPedidos() {
        return gateway.pegarFilaComPedidos();
    }

}
