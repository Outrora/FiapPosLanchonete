package br.com.lanchonete.core.domain.exception;

public class ErroValidacao extends RuntimeException {

    public ErroValidacao(String erro){
        super(erro);
    }
}
