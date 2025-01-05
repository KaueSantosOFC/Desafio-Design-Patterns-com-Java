package com.cafeteria.cafedokaue.state;

import jakarta.persistence.*;

@Entity
public class EstadoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEstado;

    public EstadoPedido() {
    }

    public EstadoPedido(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }
}
