package com.cafeteria.cafedokaue.state;

import com.cafeteria.cafedokaue.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class NovoPedidoState implements PedidoState {

    @Override
    public void avancar(Pedido pedido) {
        EstadoPedido estadoPreparacao = new EstadoPedido("Preparação");
        pedido.setEstadoAtual(estadoPreparacao);  // Definindo o estado do pedido para "Preparação"
    }

    @Override
    public void retroceder(Pedido pedido) {
        System.out.println("Já está no estado Inicial.");
    }

    @Override
    public String getNomeEstado() {
        return "Novo";
    }
}
