package br.com.lanchonete.core.domain.cozinha;

import br.com.lanchonete.core.application.cozinha.FilaProdutoDriver;
import br.com.lanchonete.core.domain.entities.FilaPedidos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FilaPedidoService {

    @Inject
    FilaProdutoDriver driver;


    public FilaPedidos criarFilaouPegarAtual() {
        return driver.criarFilaOuPegarAtual();
    }


}
