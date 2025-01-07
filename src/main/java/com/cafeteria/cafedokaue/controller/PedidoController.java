package com.cafeteria.cafedokaue.controller;

import com.cafeteria.cafedokaue.model.Pedido;
import com.cafeteria.cafedokaue.model.Produto;
import com.cafeteria.cafedokaue.service.PedidoService;
import com.cafeteria.cafedokaue.state.EstadoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        return pedidoService.criarPedido(pedido);
    }

    @PostMapping("/{pedidoId}/produtos/{produtoId}")
    public Pedido adicionarProdutoAoPedido(@PathVariable Long pedidoId, @PathVariable Long produtoId,@RequestParam Integer quantidade) {
        return pedidoService.adicionarProdutoAoPedido(pedidoId, produtoId, quantidade);
    }


    @PutMapping("/{pedidoId}/produtos/{produtoId}")
    public Pedido removerProdutoDoPedido(@PathVariable Long pedidoId, @PathVariable Long produtoId,@RequestParam Integer quantidade) {
        return pedidoService.removerProdutoDoPedido(pedidoId, produtoId, quantidade);
    }

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoService.listarPedidos();
    }

    @GetMapping("/id/{pedidoId}")
    public Pedido buscarPorId(@PathVariable Long pedidoId){
        System.out.println("ID ENCONTRADO");
        return pedidoService.buscarPorId(pedidoId);
    }

    @GetMapping("/estado/{estadoAtual}")
    public List<Pedido> buscarPorEstado(@PathVariable EstadoPedido estadoAtual){
        System.out.println("ESTADO ENCONTRADO");
        return pedidoService.buscarPorEstado(estadoAtual);
    }

    @PutMapping("/{id}/aprovar")
    public Pedido aprovarPedido(@PathVariable Long id) {
        return pedidoService.aprovarPedido(id);
    }

    @PutMapping("/{id}/rejeitar")
    public Pedido rejeitarPedido(@PathVariable Long id) {
        return pedidoService.rejeitarPedido(id);
    }

    @PutMapping("/{id}/cancelar")
    public Pedido cancelarPedido(@PathVariable Long id) {
        return pedidoService.cancelarPedido(id);
    }
}
