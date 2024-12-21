package br.com.lanchonete.core.adapters.Cliente;

import br.com.lanchonete.core.entities.Cliente;

public class ClienteRequest {
    private String nome;
    private String CPF;
    private String email;

    public ClienteRequest(String nome, String CPF, String email) {
        this.nome = nome;
        this.CPF = CPF;
        this.email = email;
    }

    public Cliente toCliente() {
        return new Cliente(nome, email, CPF);
    }
}
