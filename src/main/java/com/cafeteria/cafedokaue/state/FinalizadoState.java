package com.cafeteria.cafedokaue.state;

import com.cafeteria.cafedokaue.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class FinalizadoState implements PedidoState{
    @Override
    public void avancar(Pedido pedido) {
        System.out.println("Pedido foi finalizado e não pode avançar mais.");
    }

    @Override
    public void retroceder(Pedido pedido) {
        EstadoPedido estadoProntoParaRetirar = new EstadoPedido("Pronto para retirar.");
        pedido.setEstadoAtual(estadoProntoParaRetirar); // Define o novo estado no pedido
    }

    @Override
    public String getNomeEstado() {
        return "Finalizado";
    }
}
