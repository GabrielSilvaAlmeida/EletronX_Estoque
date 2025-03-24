package com.api.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.estoque.model.produto.DadosAtualizarProduto;
import com.api.estoque.model.produto.DadosCadastroProduto;
import com.api.estoque.model.produto.Produto;
import com.api.estoque.model.produto.ProdutoRepository;

@RestController
@RequestMapping("/api/estoque/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @PostMapping("/registro-produto")
    public ResponseEntity<Produto> cadastrarProduto (@RequestBody DadosCadastroProduto dados) {
        Produto produto = new Produto(dados);
        repository.save(produto);
        return ResponseEntity.ok(produto);
        
    }

    @GetMapping("/listar-produto")
    public ResponseEntity<List<Produto>> listarProduto () {
        List<Produto> produtos = repository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/procurar-id/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable String id) {
        return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        
    }

    @PutMapping("/editar-produto/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable String id, @RequestBody DadosAtualizarProduto dados) {
        return repository.findById(id)
            .map(produto -> {
                produto.atualizarProduto(dados);
                repository.save(produto);
                return ResponseEntity.ok(produto);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar-produto/{id}")
    public void excluir(@PathVariable String id) {
        repository.deleteById(id);
    }
}
