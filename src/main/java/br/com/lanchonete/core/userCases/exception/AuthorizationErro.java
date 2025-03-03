package br.com.lanchonete.core.userCases.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorizationErro extends RuntimeException {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationErro.class);

    public AuthorizationErro() {
        super("Campo de autorização vazio");
        log.error("Campo de autorização vazio");
    }
}
