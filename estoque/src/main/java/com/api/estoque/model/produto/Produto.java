package com.api.estoque.model.produto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "produtos")
@Entity(name = "produto")
public class Produto {
    
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    private String nomeProduto;
    private String codigoProduto;
    private String marcaProduto;
    private int quantidadeEmEstoque;
    private String modeloProduto;
    private String numeroSerieProduto;
    private LocalDateTime dataHoraAtualizacao;

    public Produto(DadosCadastroProduto dados) {
        this.nomeProduto = dados.nomeProduto();
        this.codigoProduto = dados.codigoProduto();
        this.quantidadeEmEstoque = dados.quantidadeEmEstoque();
        this.marcaProduto = dados.marcaProduto();
        this.modeloProduto = dados.modeloProduto();
        this.numeroSerieProduto = dados.numeroSerieProduto();
        this.dataHoraAtualizacao = LocalDateTime.now();
    }


    public void atualizarProduto (DadosAtualizarProduto dados) {
        if (dados.quantidadeEmEstoque() != 0) {
            this.quantidadeEmEstoque = dados.quantidadeEmEstoque();
        }

        if (dados.dataHoraAtualizacao() != null) {
            this.dataHoraAtualizacao = LocalDateTime.now();

        }

    }
}
