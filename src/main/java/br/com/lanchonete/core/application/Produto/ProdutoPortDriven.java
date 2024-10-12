package br.com.lanchonete.core.application.Produto;

import br.com.lanchonete.core.application.base.BasePortDriven;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import br.com.lanchonete.core.domain.produto.ServiceProduto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;

@ApplicationScoped
public class ProdutoPortDriven extends BasePortDriven<ProdutoAdapter, ServiceProduto> {

    public Set<Produto> pegarCategoria(Categoria categoria) {
        return service.buscarPelaCategoria(categoria);
    }

    public Boolean excluiProduto(Long id) {
        return service.excluir(id);
    }

    public void alterarProduto(Long id, Produto produto) {
        produto.alteraId(id);
        service.editarDados(produto);
    }
    
}
