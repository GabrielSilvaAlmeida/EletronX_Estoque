package com.api.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import com.api.estoque.model.funcionario.*;


@RestController
@RequestMapping("/api/estoque/")
@RequiredArgsConstructor
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping("/admin/listagem-funcionario/**") 
    public List<DadosListagemFuncionario> listar () {
        return repository.findAll().stream().map(DadosListagemFuncionario::new).toList();
    }

 @PutMapping("/{id}")
@Transactional
public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody DadosAtualizarFuncionario dados) {
    var funcionario = repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
    
    funcionario.atualizarFuncionario(dados);
    return ResponseEntity.noContent().build();
}


    @DeleteMapping("/admin/delete/{id}")
    @Transactional
    public void excluir(@PathVariable String id) {
        repository.deleteById(id);
    }

}
