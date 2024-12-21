package br.com.lanchonete.drivers.db.pedido;

import br.com.lanchonete.core.adapters.Pedido.PedidoBanco;
import br.com.lanchonete.core.adapters.Pedido.PedidoRequest;
import br.com.lanchonete.core.entities.Cliente;
import br.com.lanchonete.core.entities.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<PedidoDTO>, PedidoBanco {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public UUID inserirPedido(Pedido pedido, Long filaId, List<PedidoRequest.produtoQuantidade> produtos) {

        Long idCliente = pedido.getCliente()
                .flatMap(Cliente::getId)
                .orElse(null);

        var id = (UUID) em.createNativeQuery("""
                        INSERT INTO pedido
                        (estadopedido, preco, datacriacao, fila_id, cliente_id,id)
                        VALUES(:estadopedido, :preco, :datacriacao, :fila, :cliente,:id)
                        RETURNING id
                        """)
                .setParameter("estadopedido", pedido.getEstadoPedido().name())
                .setParameter("preco", pedido.getValorTotal())
                .setParameter("datacriacao", pedido.getDataCriacao())
                .setParameter("fila", filaId)
                .setParameter("cliente", idCliente)
                .setParameter("id", UUID.randomUUID())
                .getSingleResult();


        for (var produto : produtos) {
            inserirPedidoProduto(produto, id);
        }


        return id;
    }

    @Transactional
    void inserirPedidoProduto(PedidoRequest.produtoQuantidade produto, UUID id) {
        em.createNativeQuery("""
                        INSERT INTO pedido_produto
                        (quantidade, produto_id, pedido_id)
                        VALUES (:quantidade, :produto, :pedido)
                        """)
                .setParameter("quantidade", produto.quandidade())
                .setParameter("produto", produto.id())
                .setParameter("pedido", id)
                .executeUpdate();


    }


}
