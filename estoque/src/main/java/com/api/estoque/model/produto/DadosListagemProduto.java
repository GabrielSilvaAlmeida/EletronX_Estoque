package com.api.estoque.model.produto;

import java.time.LocalDateTime;

public record DadosListagemProduto(
    String id,
    String nomeProduto,
    String codigoProduto,
    String marcaProduto,
    int quantidadeEmEstoque,
    String modeloProduto,
    String numeroSerieProduto,
    LocalDateTime dataHoraAtualizacao
) {

    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getNomeProduto(), produto.getCodigoProduto(), produto.getMarcaProduto(), produto.getQuantidadeEmEstoque(), produto.getModeloProduto(), produto.getNumeroSerieProduto(), produto.getDataHoraAtualizacao());
    }
    
}
