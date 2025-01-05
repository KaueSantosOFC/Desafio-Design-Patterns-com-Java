package com.cafeteria.cafedokaue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private Double preco;
    @NotNull
    private Integer quantidade;
    @NotNull
    private Integer quantidadeEmEstoque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotNull Double getPreco() {
        return preco;
    }

    public void setPreco(@NotNull Double preco) {
        this.preco = preco;
    }

    public @NotNull Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(@NotNull Integer quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public @NotNull Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@NotNull Integer quantidade) {
        this.quantidade = quantidade;
    }
}
