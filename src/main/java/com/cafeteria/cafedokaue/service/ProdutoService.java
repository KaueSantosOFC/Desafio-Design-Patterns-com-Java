package com.cafeteria.cafedokaue.service;

import com.cafeteria.cafedokaue.model.Produto;
import com.cafeteria.cafedokaue.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public void adicionarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Long id,@Valid Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new NullPointerException());
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidadeEmEstoque(produtoAtualizado.getQuantidadeEmEstoque());

        return produtoRepository.save(produtoExistente);
    }

    public void verificarEstoque(){
        produtoRepository.findAll();
    }

    public void removerProduto(Long id){
        produtoRepository.deleteById(id);
    }
}
