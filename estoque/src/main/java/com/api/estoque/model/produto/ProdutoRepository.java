package com.api.estoque.model.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository <Produto, String>{
    boolean existsByNomeProduto(String nomeProduto);
}
