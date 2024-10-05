package br.com.lanchonete.core.application.interfaces;

import java.util.List;

public interface BaseDAO<T> {

    T salvar(T dados);
    boolean alterar(T dados);
    boolean excluir(T dados);
    List<T> ListarTodos();

}
