package com.cafeteria.cafedokaue.model;

import com.cafeteria.cafedokaue.state.EstadoPedido;
import com.cafeteria.cafedokaue.state.NovoPedidoState;
import com.cafeteria.cafedokaue.state.PedidoState;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
    @Id
    private Long id;
    private String descricao;
    @ManyToOne
    private EstadoPedido estadoAtual;
    @ManyToMany
    private List<Produto> produtoList = new ArrayList<>();
    private Double valorTotal;

    //Getters e Setters


    public EstadoPedido getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(EstadoPedido estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

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
}
