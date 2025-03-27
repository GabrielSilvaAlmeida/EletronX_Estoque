package com.api.estoque.model.produto;

import java.time.LocalDateTime;

public record DadosListagemProduto(
    String id,
    String nomeProduto,
    String categoria,
    String codigoProduto,
    String marcaProduto,
    String quantidadeEmEstoque,
    String modeloProduto,
    LocalDateTime dataHoraAtualizacao
) {

    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getNomeProduto(), produto.getCategoria(), produto.getCodigoProduto(), produto.getMarcaProduto(), produto.getQuantidadeEmEstoque(), produto.getModeloProduto(), produto.getDataHoraAtualizacao());
    }
    
}
