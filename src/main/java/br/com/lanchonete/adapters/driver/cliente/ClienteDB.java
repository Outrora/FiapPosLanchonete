package br.com.lanchonete.adapters.driver.cliente;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;
import java.util.UUID;

@ApplicationScoped
@Entity(name = "cliente")
public class ClienteDB {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    public String nome;
    public String email;
    public String CPF;
    public Timestamp inclucao;


}
