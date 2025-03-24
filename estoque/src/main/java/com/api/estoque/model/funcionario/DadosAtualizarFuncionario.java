package com.api.estoque.model.funcionario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarFuncionario (
    @NotNull
    String id,
    String cargo,
    String password

){
    
}
