package br.com.lanchonete.core.domain.entities;

import java.util.Optional;

public class Cliente {
    private String nome;
    private String email;
    private String cpf;
    private Optional<Long> id;


    public Cliente(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf.replaceAll("[-.]", "").trim();
        this.id = Optional.empty();
    }

    public Optional<Long> getId() {
        return id;
    }

    public void setId(Optional<Long> id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", id=" + id +
                '}';
    }
}
