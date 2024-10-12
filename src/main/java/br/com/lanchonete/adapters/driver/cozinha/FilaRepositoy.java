package br.com.lanchonete.adapters.driver.cozinha;

import br.com.lanchonete.adapters.AplicacaoMapper;
import br.com.lanchonete.core.application.cozinha.FilaDados;
import br.com.lanchonete.core.domain.entities.FilaPedidos;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;

@ApplicationScoped
public class FilaRepositoy implements PanacheRepository<FilaPedidoDTO>, FilaDados {

    @Override
    public FilaPedidos criarOuPegarFilaHoje() {
        var filaExite = find("dia", LocalDate.now()).firstResultOptional();
        if (filaExite.isPresent()) return AplicacaoMapper.INSTANCE.toFila(filaExite.get());
        var novaFila = new FilaPedidoDTO();
        novaFila.setDia(LocalDate.now());
        persist(novaFila);
        return AplicacaoMapper.INSTANCE.toFila(novaFila);
    }


}
