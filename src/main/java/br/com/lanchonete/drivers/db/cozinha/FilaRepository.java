package br.com.lanchonete.drivers.db.cozinha;

import br.com.lanchonete.core.adapters.cozinha.FilaDadosDB;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@ApplicationScoped
public class FilaRepository implements PanacheRepository<FilaPedidoDTO>, FilaDadosDB {

    @Override
    @Transactional
    public FilaPedidoDTO criarOuPegarFilaHoje() {
        var filaExite = find("dia", LocalDate.now()).firstResultOptional();

        return filaExite.orElseGet(() -> {
            var novaFila = new FilaPedidoDTO();
            novaFila.setDia(LocalDate.now());
            persist(novaFila);
            return novaFila;
        });
    }

}
