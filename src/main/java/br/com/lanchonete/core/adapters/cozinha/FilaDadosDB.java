package br.com.lanchonete.core.adapters.cozinha;

import br.com.lanchonete.drivers.db.cozinha.FilaPedidoDTO;

public interface FilaDadosDB {

    FilaPedidoDTO criarOuPegarFilaHoje();

}