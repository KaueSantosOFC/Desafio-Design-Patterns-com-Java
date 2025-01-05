package com.cafeteria.cafedokaue.state;

import com.cafeteria.cafedokaue.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class ProntoParaRetiradaState implements PedidoState{
    @Override
    public void avancar(Pedido pedido) {
        EstadoPedido estadoFinalizado = new EstadoPedido("Finalizado.");
        pedido.setEstadoAtual(estadoFinalizado);
    }

    @Override
    public void retroceder(Pedido pedido) {
        EstadoPedido estadoPreparacao = new EstadoPedido("Preparação.");
        pedido.setEstadoAtual(estadoPreparacao); // Define o novo estado no pedido
    }

    @Override
    public String getNomeEstado() {
        return "Pronto para Retirada";
    }
}
