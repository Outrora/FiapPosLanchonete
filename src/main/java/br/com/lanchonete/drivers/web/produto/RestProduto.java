package br.com.lanchonete.drivers.web.produto;

import br.com.lanchonete.core.adapters.Produto.ProdutoController;
import br.com.lanchonete.core.entities.Produto;
import br.com.lanchonete.core.entities.enums.Categoria;
import br.com.lanchonete.core.userCases.produto.ProdutoAdapter;
import br.com.lanchonete.drivers.web.base.RestBase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Set;

@Path("produto")
@RequestScoped
@Tag(name = "Produto", description = "Endpoints do produto")
public class RestProduto extends RestBase<ProdutoController> {

    @GET()
    @Path("categoria/{categoria}")
    public Set<Produto> pegarProdutoCategoria(@PathParam("categoria") Categoria categoria) {
        return controller.pegarCategoria(categoria);
    }

    @POST
    public Response inserir(ProdutoAdapter adapter) {
        var produto = adapter.adapter();
        controller.salvarProduto(produto);
        return respostaSucesso();
    }

    @DELETE()
    @Path("{id}")
    public Response deletarProduto(@PathParam("id") Long id) {
        var excluido = controller.excluiProduto(id);
        if (excluido)
            return this.respostaSucesso("Produto deledado com sucesso", 200);
        return this.respostaErro();
    }

    @PUT()
    @Path("{id}")
    public Response alteraProduto(@PathParam("id") Long id, ProdutoAdapter adapter) {
        var produto = adapter.adapter();
        controller.alterarProduto(id, produto);
        return this.respostaSucesso("Produto alterado com sucesso", 200);
    }
}
