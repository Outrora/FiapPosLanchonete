package br.com.lanchonete.core.application.interfaces;


public interface BaseDAO<T> {

    void salvar(T dados);

    boolean alterar(T dados);

    boolean excluir(T dados);
}
