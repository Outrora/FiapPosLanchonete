package br.com.lanchonete.core.adapters.Produto;

import br.com.lanchonete.core.adapters.base.BaseController;
import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.entities.enums.Categoria;
import br.com.lanchonete.core.userCases.produto.BuscarProdutoUseCase;
import br.com.lanchonete.core.userCases.produto.EditarProdutoUseCase;
import br.com.lanchonete.core.userCases.produto.ExcluirProdutoUseCase;
import br.com.lanchonete.core.userCases.produto.SalvarProdutoUserCase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Set;

@RequestScoped
public class ProdutoController extends BaseController {

    @Inject
    IProdutoGateway gateway;

    @Inject
    SalvarProdutoUserCase salvarProdutoUserCase;

    @Inject
    ExcluirProdutoUseCase excluirProdutoUseCase;

    @Inject
    EditarProdutoUseCase editarProdutoUseCase;

    @Inject
    BuscarProdutoUseCase busacarProdutoUseCase;

    public void salvarProduto(Produto produto) {
        salvarProdutoUserCase.salvarDados(produto);
    }

    public Set<Produto> pegarCategoria(Categoria categoria) {
        return busacarProdutoUseCase.buscarPelaCategoria(categoria);
    }

    public Boolean excluiProduto(Long id) {
        return excluirProdutoUseCase.excluir(id);
    }

    public void alterarProduto(Long id, Produto produto) {

        produto.alteraId(id);
        editarProdutoUseCase.editarDados(produto);
    }

}
