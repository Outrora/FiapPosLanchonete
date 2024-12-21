package br.com.lanchonete.core.adapters.Produto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.entities.enums.Categoria;
import jakarta.transaction.Transactional;

public interface IProdutoGateway {

    public void salvar(Produto dados);

    public Optional<Produto> pegarId(Long id);

    public List<Produto> pegarId(Set<Long> id);

    public Set<Produto> buscarPorCategoria(Categoria dados);

    @Transactional
    public void alterar(Produto dados);

    @Transactional
    public boolean excluir(Long id);

}
