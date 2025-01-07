package com.cafeteria.cafedokaue.repository;

import com.cafeteria.cafedokaue.model.Pedido;
import com.cafeteria.cafedokaue.state.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEstadoAtual(EstadoPedido estadoAtual);
}
