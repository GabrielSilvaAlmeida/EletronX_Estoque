package com.api.estoque.model.produto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarProduto(
    @NotNull
    String id,
    int quantidadeEmEstoque,
    LocalDateTime dataHoraAtualizacao
) {
    
}
