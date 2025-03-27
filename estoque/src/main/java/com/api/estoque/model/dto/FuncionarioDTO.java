package com.api.estoque.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank
    @Size(min = 3, max = 50)
    private String cargo;

    @NotBlank
    @Size(min = 3, max = 50)
    private String password;

    @NotBlank
    @Email
    private String email;


}
