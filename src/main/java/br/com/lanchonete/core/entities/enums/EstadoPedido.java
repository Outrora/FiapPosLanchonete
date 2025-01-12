package br.com.lanchonete.core.entities.enums;

public enum EstadoPedido {
    PEDIDO_CADASTRADO("Esperando pagamento", false, 0),
    PAGAMENTO_APROVADO("Pagamento Aprovado", true, 1),
    PAGAMENTO_RECUSADO("Pagamento Recursado", true, 0),
    EM_PREPARACAO("Pagamento Aprovado", true, 2),
    PRONTO("Pagamento Aprovado", true, 3),
    FINALIZADO("Pagamento Aprovado", true, 0);

    private String estadoPagamento;
    private Boolean pagamentoRelizado;
    private Integer ordem;

    EstadoPedido(String estadoPagamento, Boolean pagamentoRelizado, Integer ordemOrdenacao) {
        this.estadoPagamento = estadoPagamento;
        this.pagamentoRelizado = pagamentoRelizado;
        this.ordem = ordemOrdenacao;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public String getEstadoPagamento() {
        return estadoPagamento;
    }

    public Boolean getPagamentoRelizado() {
        return pagamentoRelizado;
    }

}
