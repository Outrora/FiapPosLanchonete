package br.com.lanchonete.core.application.Produto;


import br.com.lanchonete.adapters.driver.produto.ProdutoRepository;
import br.com.lanchonete.core.application.base.BasePortDiver;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import br.com.lanchonete.core.domain.exception.ResultadaoVazioErro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoPortDriver extends BasePortDiver<Produto, ProdutoRepository> {


    private static final Logger log = LoggerFactory.getLogger(ProdutoPortDriver.class);


    @Transactional
    @Override
    public void salvar(Produto dados) {
        repository.cadastrarProduto(dados);
    }

    public Set<Produto> buscarPorCategoria(Categoria dados) {
        return repository.buscarPorCatergoria(dados)
                .stream()
                .map(dto -> {
                    return new Produto(dto.getNome(),
                            dto.getDescricao(),
                            dto.getPreco(),
                            dto.getCategoria(),
                            Optional.of(dto.getId())

                    );
                })
                .collect(Collectors.toSet());
    }


    @Transactional
    public void alterar(Produto dados) {
        if (dados.id().isEmpty()) {
            throw new ResultadaoVazioErro("Produto não encontrado");
        }
        repository.editarProduto(dados);
    }

    @Transactional
    public boolean excluir(Long id) {
        return repository.removerProduto(id);
    }

    public Optional<Produto> buscarId(Long id) {
        var dto = repository.findByIdOptional(id);

        return dto.map(dto1 -> {
            return new Produto(
                    dto1.getNome(),
                    dto1.getDescricao(),
                    dto1.getPreco(),
                    dto1.getCategoria(),
                    Optional.of(dto1.getId()));
        });
    }
}
