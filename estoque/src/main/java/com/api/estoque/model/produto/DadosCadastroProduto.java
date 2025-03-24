package com.api.estoque.model.produto;

import java.time.LocalDateTime;

public record DadosCadastroProduto(String nomeProduto, String codigoProduto, int quantidadeEmEstoque, String marcaProduto, String modeloProduto, String numeroSerieProduto, LocalDateTime dataHoraAtualizacao) {
    
}
