package br.com.lanchonete.core.application.base;


import jakarta.inject.Inject;

import java.util.logging.Logger;

public abstract class BasePortDiver<T, R> {


    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Inject
    protected R repository;

    public abstract void salvar(T dados);


}
