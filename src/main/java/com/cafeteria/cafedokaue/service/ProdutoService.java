package com.cafeteria.cafedokaue.service;

import com.cafeteria.cafedokaue.model.Produto;
import com.cafeteria.cafedokaue.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    //Adiciona um produto ao banco de dados.
    public Produto criarProduto(@Valid Produto produto) {
       return produtoRepository.save(produto);
    }

    //Remove um produto do Banco de dados.
    public void removerProduto(Long id){
        produtoRepository.deleteById(id);
    }

    //Atualiza um produto no banco de dados.
    public Produto atualizarProduto(Long id,@Valid Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new NullPointerException());
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());
        produtoExistente.setQuantidadeEmEstoque(produtoAtualizado.getQuantidadeEmEstoque());

        return produtoRepository.save(produtoExistente);
    }

    //Retorna a Lista de todos os produtos.
    public List<Produto> verificarEstoque() {
        return produtoRepository.findAll(); // Certifique-se de que este método está funcionando.
    }
}
