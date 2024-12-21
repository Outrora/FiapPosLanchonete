package br.com.lanchonete.core.adapters.cozinha;

import br.com.lanchonete.core.entities.FilaPedidos;

public interface FilaDadosDB {

    FilaPedidos criarOuPegarFilaHoje();

    FilaPedidos pegarFilaComPedidos();

}