package br.com.lanchonete.adapters.driven.produto;

import br.com.lanchonete.adapters.AplicacaoMapper;
import br.com.lanchonete.adapters.driven.base.RestBase;
import br.com.lanchonete.core.application.Produto.ProdutoAdapter;
import br.com.lanchonete.core.application.Produto.ProdutoPortDriven;
import br.com.lanchonete.core.domain.entities.Produto;
import br.com.lanchonete.core.domain.enums.Categoria;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Set;

@Path("produto")
@RequestScoped
@Tag(name = "Produto", description = "Endpoints do produto")
public class RestProduto extends RestBase<ProdutoAdapter, ProdutoPortDriven> {

    @GET()
    @Path("categoria/{categoria}")
    public Set<Produto> pegarProdutoCategoria(@PathParam("categoria") Categoria categoria) {
        return driven.pegarCategoria(categoria);
    }

    @DELETE()
    @Path("{id}")
    public Response deletarProduto(@PathParam("id") Long id) {
        var excluido = driven.excluiProduto(id);
        if (excluido) return this.respostaSucesso("Produto deledado com sucesso", 200);
        return this.respostaErro();
    }

    @PUT()
    @Path("{id}")
    public Response alteraProduto(@PathParam("id") Long id, ProdutoAdapter adapter) {
        var produto = AplicacaoMapper.INSTANCE.toProduto(adapter);
        driven.alterarProduto(id, produto);
        return this.respostaSucesso("Produto alterado com sucesso", 200);
    }
}
