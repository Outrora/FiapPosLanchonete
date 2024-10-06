package br.com.lanchonete.core.application.base;

import br.com.lanchonete.core.domain.base.ServiceBase;
import jakarta.inject.Inject;

import java.util.logging.Logger;

public abstract class BasePortDriven<A extends AdapterBase, S extends ServiceBase> {

    @Inject
    protected S service;

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    public void salvar(A adapter) {
        LOG.info("Salvando Dados");
        service.salvarDados(adapter.adapter());
    }


}
