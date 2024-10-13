package br.com.lanchonete.core.application.cozinha;

import br.com.lanchonete.core.domain.entities.FilaPedidos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class FilaProdutoDriver {
    private static final Logger log = LoggerFactory.getLogger(FilaProdutoDriver.class);

    @Inject
    FilaDados fila;

    @Transactional
    public FilaPedidos criarFilaOuPegarAtual() {

        return fila.criarOuPegarFilaHoje();
    }

    public FilaPedidos pegarFilaComPedidos() {
        log.info("Pegado Fila Atual");
        return fila.pegarFilaComPedidos();
    }


}
