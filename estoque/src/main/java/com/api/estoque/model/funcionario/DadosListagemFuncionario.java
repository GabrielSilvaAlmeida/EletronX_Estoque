package com.api.estoque.model.funcionario;

public record DadosListagemFuncionario(
    String id,
    String username,
    String nome,
    String cargo,
    String password,
    String email
) {

    public DadosListagemFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getUsername(), funcionario.getNome(), funcionario.getCargo(), funcionario.getPassword(), funcionario.getEmail());
    }
    
}
