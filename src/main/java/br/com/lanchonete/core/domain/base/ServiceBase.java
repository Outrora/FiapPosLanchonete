package br.com.lanchonete.core.domain.base;

public interface ServiceBase<T> {
    void salvarDados(T dados);
}
