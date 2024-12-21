package br.com.lanchonete.core.userCases.produto;

import br.com.lanchonete.core.adapters.Produto.IProdutoGateway;
import br.com.lanchonete.core.userCases.base.UseCaseBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExcluirProdutoUseCase implements UseCaseBase {

    @Inject
    IProdutoGateway gateway;

    public boolean excluir(Long id) {
        return gateway.excluir(id);
    }

}
