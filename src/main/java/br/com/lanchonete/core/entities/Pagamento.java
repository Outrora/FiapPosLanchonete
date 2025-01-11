package br.com.lanchonete.core.entities;

import java.util.UUID;

public record Pagamento(UUID id, String formaPagamento) {
}
