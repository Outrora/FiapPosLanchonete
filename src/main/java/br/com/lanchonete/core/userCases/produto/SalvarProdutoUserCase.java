package br.com.lanchonete.core.userCases.produto;

import br.com.lanchonete.core.adapters.Produto.IProdutoGateway;
import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.userCases.base.UseCaseBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SalvarProdutoUserCase implements UseCaseBase {

    @Inject
    IProdutoGateway gateway;

    public void salvarDados(Produto dados) {
        gateway.salvar(dados);
    }
}
