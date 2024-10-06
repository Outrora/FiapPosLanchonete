package br.com.lanchonete.core.domain.exception;

public class ResultadaoVazioErro extends RuntimeException {
    public ResultadaoVazioErro(String message) {
        super(message);
    }
}
