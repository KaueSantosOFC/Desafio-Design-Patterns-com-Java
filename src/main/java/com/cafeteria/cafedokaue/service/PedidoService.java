package com.cafeteria.cafedokaue.service;

import com.cafeteria.cafedokaue.model.Pedido;
import com.cafeteria.cafedokaue.model.Produto;
import com.cafeteria.cafedokaue.repository.PedidoRepository;
import com.cafeteria.cafedokaue.repository.ProdutoRepository;
import com.cafeteria.cafedokaue.state.EstadoPedido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProdutoRepository produtoRepository;


    //Cria o pedido (Não inicia com nenhum produto).
    @Transactional
        public Pedido criarPedido(Pedido pedido){
            pedido.setProdutoList(new ArrayList<>()); // Garantir lista vazia
            pedido.setValorTotal(0.0); // Valor inicial como 0
            pedido.setEstadoAtual(EstadoPedido.NOVO_PEDIDO);
            return pedidoRepository.save(pedido);
        }

        public void removerPedido(Long pedidoId){pedidoRepository.deleteById(pedidoId);}

    //Selecione o ID do pedido já criado e adicione os produtos.
    @Transactional
    public Pedido adicionarProdutoAoPedido(Long pedidoId, Long produtoId, Integer quantidade){
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidadeEmEstoque() < quantidade) {
                throw new RuntimeException("Quantidade em estoque insuficiente");
            }
        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidade);
        produto.setQuantidade(quantidade);

        pedido.getProdutoList().add(produto);
        calcularValorTotal(pedido);
        return pedidoRepository.save(pedido);
        }

    //Remove um produto de um Pedido.
    @Transactional
    public Pedido removerProdutoDoPedido(Long pedidoId, Long produtoId, Integer quantidade){
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Localizar o produto na lista do pedido
        Produto produtoNoPedido = pedido.getProdutoList().stream()
                .filter(p -> p.getId().equals(produtoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não está no pedido"));

        // Verificar se a quantidade a ser removida é válida
        if (quantidade > produtoNoPedido.getQuantidade()) {
            throw new RuntimeException("Quantidade a ser removida é maior do que a quantidade no pedido");
        }

        // Atualizar o estoque e a quantidade no pedido
        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + quantidade);
        produtoNoPedido.setQuantidade(produtoNoPedido.getQuantidade() - quantidade);

        produtoRepository.save(produto);

        // Remover o produto se a quantidade no pedido for 0
        if (produtoNoPedido.getQuantidade() <= 0) {
            pedido.getProdutoList().remove(produtoNoPedido);
        }


        calcularValorTotal(pedido);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {return pedidoRepository.findAll();}

    public Pedido buscarPorId(Long pedidoId){
        Pedido pedidoEncontrado = pedidoRepository.findById(pedidoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não existe!"));
        System.out.println("SUCESSO AO BUSCAR PEDIDO");
        return pedidoEncontrado;
    }

    public List<Pedido> buscarPorEstado(EstadoPedido estadoAtual){
        return pedidoRepository.findByEstadoAtual(estadoAtual);
    }
    //Altera o status do pedido.
    public Pedido aprovarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));
        pedido.aprovar();
        return pedidoRepository.save(pedido);
    }

    //Altera o status do pedido.
    public Pedido rejeitarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));
        pedido.rejeitar();
        return pedidoRepository.save(pedido);
    }

    //Altera o status do pedido.
    public Pedido cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));
        pedido.cancelar();
        return pedidoRepository.save(pedido);
    }


    //Somente usado no service
    private void calcularValorTotal(Pedido pedido) {
        Double valorTotal = 0.0;
        for (Produto produto : pedido.getProdutoList()) {
            valorTotal += produto.getPreco() * produto.getQuantidade();

        }
        pedido.setValorTotal(valorTotal);
    }
}