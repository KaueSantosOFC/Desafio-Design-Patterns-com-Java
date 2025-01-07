package com.cafeteria.cafedokaue.controller;

import com.cafeteria.cafedokaue.model.Produto;
import com.cafeteria.cafedokaue.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @PostMapping
    public Produto criarProduto(@Valid @RequestBody Produto produto) {
        return produtoService.criarProduto(produto);
    }

    @GetMapping
    public List<Produto> verificarEstoque(){
        return produtoService.verificarEstoque();
    }

    @DeleteMapping("{produtoId}")
    public void removerProduto(@PathVariable Long produtoId) {
        produtoService.removerProduto(produtoId);
    }

    @PutMapping("{id}")
    public Produto atualizarProduto(@PathVariable Long id,@RequestBody @Valid Produto produtoAtualizado) {
        return produtoService.atualizarProduto(id, produtoAtualizado);
    }

}
