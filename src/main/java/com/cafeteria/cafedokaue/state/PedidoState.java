package com.cafeteria.cafedokaue.state;

import com.cafeteria.cafedokaue.model.Pedido;


public interface PedidoState {
    void avancar(Pedido pedido);
    void retroceder(Pedido pedido);
    String getNomeEstado();
}
