package com.cafeteria.cafedokaue.dbSeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cafeteria.cafedokaue.model.Produto;
import com.cafeteria.cafedokaue.repository.ProdutoRepository;

import java.util.Arrays;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) {
        // Verifica se o banco já está populado
        if (produtoRepository.count() == 0) {
            // Lista de produtos para inserir
            Produto p1 = new Produto(null, "Café Expresso", 5.00, 50, 50);
            Produto p2 = new Produto(null, "Café com Leite", 6.50, 40, 40);
            Produto p3 = new Produto(null, "Cappuccino", 7.00, 30, 30);
            Produto p4 = new Produto(null, "Chá Gelado", 4.50, 20, 20);
            Produto p5 = new Produto(null, "Bolo de Cenoura", 8.00, 15, 15);
            Produto p6 = new Produto(null, "Torrada Simples", 3.00, 25, 25);
            Produto p7 = new Produto(null, "Croissant", 4.50, 20, 20);
            Produto p8 = new Produto(null, "Suco Natural", 6.00, 10, 10);
            Produto p9 = new Produto(null, "Chocolate Quente", 6.50, 30, 30);
            Produto p10 = new Produto(null, "Café Mocha", 7.50, 20, 20);

            // Salva todos os produtos no banco
            produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

            System.out.println("Banco de dados populado com produtos iniciais!");
        } else {
            System.out.println("Banco de dados já possui produtos cadastrados.");
        }
    }
}

