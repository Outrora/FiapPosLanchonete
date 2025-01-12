package br.com.lanchonete.core.adapters.cozinha;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import br.com.lanchonete.core.adapters.base.BaseController;
import br.com.lanchonete.core.entities.FilaPedidos;

@ApplicationScoped
public class FilaProdutoGateway extends BaseController implements IFilaGateway {

        @Inject
        FilaDadosDB fila;

        @Override
        public FilaPedidos criarOuPegarFilaHoje() {
                LOG.info("Pegado Fila Atual");
                var filaAtual = fila.criarOuPegarFilaHoje();
                return FilaPedidoMapper.toFilaPedidos(filaAtual);
        }

}
