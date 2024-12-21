package br.com.lanchonete.core.userCases.produto;

import br.com.lanchonete.core.adapters.Produto.IProdutoGateway;
import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.userCases.base.UseCaseBase;
import br.com.lanchonete.core.userCases.exception.ResultadoVazioErro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EditarProdutoUseCase implements UseCaseBase {

    @Inject
    IProdutoGateway gateway;

    public void editarDados(Produto produto) {
        if (produto.getId().isEmpty()) {
            throw new ResultadoVazioErro("Produto não encontrado");
        }
        var produtoSalvo = gateway.pegarId(produto.getId().get());
        if (produtoSalvo.isEmpty()) {
            throw new ResultadoVazioErro("Produto não encontrado");
        }
        gateway.alterar(produto);

    }
}
