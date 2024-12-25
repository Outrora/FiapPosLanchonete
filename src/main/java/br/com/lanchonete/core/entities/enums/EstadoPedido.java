package br.com.lanchonete.core.entities.enums;

public enum EstadoPedido {
    PEDIDO_CADASTRADO("Esperando pagamento", false),
    PAGAMENTO_APROVADO("Pagamento Aprovado", true),
    PAGAMENTO_RECUSADO("Pagamento Recursado", true),
    EM_PREPARACAO("Pagamento Aprovado", true),
    PRONTO("Pagamento Aprovado", true),
    PEDIDO_ENTREGUE("Pagamento Aprovado", true);

    private String estadoPagamento;
    private Boolean pagamentoRelizado;

    EstadoPedido(String estadoPagamento, Boolean pagamentoRelizado) {
        this.estadoPagamento = estadoPagamento;
        this.pagamentoRelizado = pagamentoRelizado;
    }

    public String getEstadoPagamento() {
        return estadoPagamento;
    }

    public Boolean getPagamentoRelizado() {
        return pagamentoRelizado;
    }

}
