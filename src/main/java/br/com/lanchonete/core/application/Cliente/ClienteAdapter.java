package br.com.lanchonete.core.application.Cliente;

import br.com.lanchonete.core.application.base.AdapterBase;
import br.com.lanchonete.core.domain.entities.Cliente;

public class ClienteAdapter implements AdapterBase<Cliente> {
    private String nome;
    private String email;
    private String cpf;

    public ClienteAdapter(String nome, String email, String CPF) {
        this.nome = nome;
        this.email = email;
        this.cpf = CPF;
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
    public Cliente adapter() {
        var cpfLimpo = cpf.replaceAll("[-.]", "").trim();
        return new Cliente(nome, email, cpfLimpo);
    }
}
