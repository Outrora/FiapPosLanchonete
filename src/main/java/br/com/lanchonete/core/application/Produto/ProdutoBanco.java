package br.com.lanchonete.core.application.Produto;

import br.com.lanchonete.adapters.driver.produto.ProdutoDTO;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;

import java.util.List;
import java.util.Optional;

public interface ProdutoBanco {

    List<ProdutoDTO> buscarPorCatergoria(Categoria categoria);

    void cadastrarProduto(Produto produto);

    void editarProduto(Produto produto);

    boolean removerProduto(Long id);

    Optional<Produto> buscarId(Long id);
}
