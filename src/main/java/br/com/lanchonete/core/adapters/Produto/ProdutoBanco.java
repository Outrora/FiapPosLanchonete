package br.com.lanchonete.core.adapters.Produto;

import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.entities.enums.Categoria;
import br.com.lanchonete.drivers.db.produto.ProdutoDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProdutoBanco {

    List<ProdutoDTO> buscarPorCatergoria(Categoria categoria);

    void cadastrarProduto(Produto produto);

    void editarProduto(Produto produto);

    boolean removerProduto(Long id);

    Optional<Produto> buscarId(Long id);

    List<Produto> buscarId(Set<Long> id);
}
