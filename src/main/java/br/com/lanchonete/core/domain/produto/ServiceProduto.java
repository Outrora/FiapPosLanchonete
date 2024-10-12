package br.com.lanchonete.core.domain.produto;

import br.com.lanchonete.core.application.Produto.ProdutoPortDriver;
import br.com.lanchonete.core.domain.base.ServiceBase;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import br.com.lanchonete.core.domain.exception.ResultadaoVazioErro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ServiceProduto implements ServiceBase<Produto> {

    @Inject
    ProdutoPortDriver driver;


    public void salvarDados(Produto dados) {
        driver.salvar(dados);
    }

    public Set<Produto> buscarPelaCategoria(Categoria categoria) {
        return driver.buscarPorCategoria(categoria);
    }

    public boolean excluir(Long id) {
        return driver.excluir(id);
    }

    public List<Produto> pegarPelosId(Set<Long> ids) {
        return driver.pegarId(ids);
    }

    public void editarDados(Produto produto) {
        if (produto.getId().isEmpty()) {
            throw new ResultadaoVazioErro("Produto não encontrado");
        }
        var produtoSalvo = driver.pegarId(produto.getId().get());
        if (produtoSalvo.isEmpty()) {
            throw new ResultadaoVazioErro("Produto não encontrado");
        }
        driver.alterar(produto);

    }
}
