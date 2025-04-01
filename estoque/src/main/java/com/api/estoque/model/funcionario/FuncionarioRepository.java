package com.api.estoque.model.funcionario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository <Funcionario, String>{
    Optional<Funcionario> findByEmail(String email);
}
