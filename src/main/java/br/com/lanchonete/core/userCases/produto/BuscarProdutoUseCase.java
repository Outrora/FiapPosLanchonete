package br.com.lanchonete.core.userCases.produto;

import java.util.List;
import java.util.Set;

import br.com.lanchonete.core.adapters.Produto.IProdutoGateway;
import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.entities.enums.Categoria;
import br.com.lanchonete.core.userCases.base.UseCaseBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BuscarProdutoUseCase implements UseCaseBase {

    @Inject
    IProdutoGateway gateway;

    public List<Produto> pegarPelosId(Set<Long> ids) {
        return gateway.pegarId(ids);
    }

    public Set<Produto> buscarPelaCategoria(Categoria categoria) {
        return gateway.buscarPorCategoria(categoria);
    }

}
