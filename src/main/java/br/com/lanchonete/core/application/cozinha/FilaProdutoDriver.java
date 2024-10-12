package br.com.lanchonete.core.application.cozinha;

import br.com.lanchonete.core.domain.entities.FilaPedidos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FilaProdutoDriver {

    @Inject
    FilaDados fila;

    @Transactional
    public FilaPedidos criarFilaOuPegarAtual() {
        return fila.criarOuPegarFilaHoje();
    }


}
