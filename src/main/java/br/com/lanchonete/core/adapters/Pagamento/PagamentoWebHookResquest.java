package br.com.lanchonete.core.adapters.Pagamento;

import java.util.UUID;

public class PagamentoWebHookResquest {
    private UUID pedidoId;
    private String statusPagamento;

    // Getters e Setters

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
