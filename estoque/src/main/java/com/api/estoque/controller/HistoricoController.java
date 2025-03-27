package com.api.estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.estoque.model.service.HistoricoService;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    private final HistoricoService historicoService;

    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @PostMapping("/solicitar/{funcionarioId}/{produtoId}")
    public ResponseEntity<String> solicitarProduto(@PathVariable String funcionarioId, @PathVariable String produtoId) {
        historicoService.adicionarAoHistorico(funcionarioId, produtoId);
        return ResponseEntity.ok("Produto adicionado ao histórico com sucesso!");
    }
}