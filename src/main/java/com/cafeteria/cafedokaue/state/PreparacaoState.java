package com.cafeteria.cafedokaue.state;

import com.cafeteria.cafedokaue.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PreparacaoState implements PedidoState{
    @Override
    public void avancar(Pedido pedido) {
        EstadoPedido estadoProntoParaRetirada = new EstadoPedido("Pronto para retirada.");
        pedido.setEstadoAtual(estadoProntoParaRetirada);
    }

    @Override
    public void retroceder(Pedido pedido) {
        EstadoPedido estadoNovo = new EstadoPedido("Novo");
        pedido.setEstadoAtual(estadoNovo); // Define o novo estado no pedido
    }

    @Override
    public String getNomeEstado() {
        return "Em Preparação";
    }
}
