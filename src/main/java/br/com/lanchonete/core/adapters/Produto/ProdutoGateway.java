package br.com.lanchonete.core.adapters.Produto;

import br.com.lanchonete.core.adapters.base.BaseGateway;
import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.entities.enums.Categoria;
import br.com.lanchonete.drivers.AplicacaoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoGateway extends BaseGateway implements IProdutoGateway {

    @Inject
    ProdutoBanco bancoDados;

    @Transactional
    @Override
    public void salvar(Produto dados) {
        bancoDados.cadastrarProduto(dados);
    }

    @Override
    public Optional<Produto> pegarId(Long id) {
        LOG.info("Pegando Produdo pelo ID");
        return bancoDados.buscarId(id);
    }

    public List<Produto> pegarId(Set<Long> id) {
        LOG.info("Pegando Produtos pelo IDs");
        return bancoDados.buscarId(id);
    }

    public Set<Produto> buscarPorCategoria(Categoria dados) {
        LOG.info("Pegando Produdo pelo Cetegoria");
        return bancoDados.buscarPorCatergoria(dados)
                .stream()
                .map(AplicacaoMapper.INSTANCE::toProduto)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void alterar(Produto dados) {
        LOG.info("Alterando produtos");
        bancoDados.editarProduto(dados);
    }

    @Transactional
    public boolean excluir(Long id) {
        LOG.info("Excluindo Produto");
        return bancoDados.removerProduto(id);
    }

}
