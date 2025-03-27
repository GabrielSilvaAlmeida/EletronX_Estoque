package com.api.estoque.model.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.api.estoque.model.funcionario.Funcionario;
import com.api.estoque.model.funcionario.FuncionarioRepository;
import com.api.estoque.model.historico.Historico;
import com.api.estoque.model.historico.HistoricoRepository;
import com.api.estoque.model.produto.Produto;
import com.api.estoque.model.produto.ProdutoRepository;

@Service
public class HistoricoService {

    private final HistoricoRepository historicoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProdutoRepository produtoRepository;

    public HistoricoService(HistoricoRepository historicoRepository, FuncionarioRepository funcionarioRepository, ProdutoRepository produtoRepository) {
        this.historicoRepository = historicoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.produtoRepository = produtoRepository;
    }

    public void adicionarAoHistorico(String funcionarioId, String produtoId) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        Historico historico = new Historico();
        historico.setProduto(produto);
        historico.setDataAquisicao(LocalDate.now());

        // Adiciona ao funcionário
        funcionario.getHistoricoFuncionario().add(historico);

        // Salva no banco
        historicoRepository.save(historico);
        funcionarioRepository.save(funcionario);
    }
}
