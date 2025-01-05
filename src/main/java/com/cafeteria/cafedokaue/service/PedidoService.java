package com.cafeteria.cafedokaue.service;

import com.cafeteria.cafedokaue.model.Pedido;
import com.cafeteria.cafedokaue.model.Produto;
import com.cafeteria.cafedokaue.repository.PedidoRepository;
import com.cafeteria.cafedokaue.repository.ProdutoRepository;
import com.cafeteria.cafedokaue.state.EstadoPedido;
import com.cafeteria.cafedokaue.state.NovoPedidoState;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
        public Pedido criarPedido(Pedido pedido){
            for(Produto p : pedido.getProdutoList()) {
                Produto produto = produtoRepository.findById(p.getId()).orElseThrow(() -> new NullPointerException());
                produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - p.getQuantidade());
                produtoRepository.save(produto);
            }

            calcularValorTotal(pedido);
            EstadoPedido estadoNovo = new EstadoPedido("Novo");
            pedido.setEstadoAtual(estadoNovo);
            return pedidoRepository.save(pedido);

        }

    private void calcularValorTotal(Pedido pedido) {
        Double valorTotal = 0.0;
        for (Produto produto : pedido.getProdutoList()) {
            valorTotal += produto.getPreco() * produto.getQuantidade();

        }
        pedido.setValorTotal(valorTotal);
    }
}