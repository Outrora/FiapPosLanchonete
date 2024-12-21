package br.com.lanchonete.core.adapters.cozinha;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import br.com.lanchonete.core.adapters.base.BaseController;
import br.com.lanchonete.core.entities.FilaPedidos;

@ApplicationScoped
public class FilaProdutoGateway extends BaseController implements IFilaGateway {

    @Inject
    FilaDadosDB fila;

    @Override
    public FilaPedidos pegarFilaComPedidos() {
        LOG.info("Pegado Fila Atual");
        return fila.pegarFilaComPedidos();
    }

    @Override
    @Transactional
    public FilaPedidos criarOuPegarFilaHoje() {
        return fila.criarOuPegarFilaHoje();
    }

}
