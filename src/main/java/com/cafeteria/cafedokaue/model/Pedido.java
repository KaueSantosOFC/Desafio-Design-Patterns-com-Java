package com.cafeteria.cafedokaue.model;

import com.cafeteria.cafedokaue.state.EstadoPedido;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoAtual = EstadoPedido.NOVO_PEDIDO;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtoList = new ArrayList<>();

    private Double valorTotal;


    //Metódos para passar o estado atual do pedido
    public void aprovar() {
        estadoAtual.aprovar(this);
    }

    public void rejeitar() {
        estadoAtual.rejeitar(this);
    }

    public void cancelar() {
        estadoAtual.cancelar(this);
    }

    //Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public EstadoPedido getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(EstadoPedido estadoAtual) {
        this.estadoAtual = estadoAtual;
    }
}
