package br.com.lanchonete.core.domain.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultadaoVazioErro extends RuntimeException {
    private static final Logger log = LoggerFactory.getLogger(ResultadaoVazioErro.class);

    public ResultadaoVazioErro(String message) {
        super(message);
        log.error("ResultadaoVazioErro: {}", message);
    }
}
