package br.com.lanchonete.core.application.Produto;


import br.com.lanchonete.adapters.AplicacaoMapper;
import br.com.lanchonete.core.application.base.BasePortDiver;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoPortDriver extends BasePortDiver<Produto, ProdutoBanco> {


    private static final Logger log = LoggerFactory.getLogger(ProdutoPortDriver.class);


    @Transactional
    @Override
    public void salvar(Produto dados) {
        repository.cadastrarProduto(dados);
    }

    @Override
    public Optional<Produto> pegarId(Long id) {
        log.info("Pegando Produdo pelo ID");
        return repository.buscarId(id);
    }

    public List<Produto> pegarId(Set<Long> id) {
        log.info("Pegando Produtos pelo IDs");
        return repository.buscarId(id);
    }

    public Set<Produto> buscarPorCategoria(Categoria dados) {
        log.info("Pegando Produdo pelo Cetegoria");
        return repository.buscarPorCatergoria(dados)
                .stream()
                .map(AplicacaoMapper.INSTANCE::toProduto)
                .collect(Collectors.toSet());
    }


    @Transactional
    public void alterar(Produto dados) {
        log.info("Alterando produtos");
        repository.editarProduto(dados);
    }

    @Transactional
    public boolean excluir(Long id) {
        log.info("Excluindo Produto");
        return repository.removerProduto(id);
    }


}
