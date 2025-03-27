package com.api.estoque.model.produto;

import java.time.LocalDateTime;

public record DadosCadastroProduto(String nomeProduto, String categoria, String codigoProduto, String quantidadeEmEstoque, String marcaProduto, String modeloProduto, String numeroSerieProduto, LocalDateTime dataHoraAtualizacao) {
    
}
