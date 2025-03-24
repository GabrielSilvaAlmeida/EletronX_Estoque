package com.api.estoque.model.service;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.estoque.model.dto.FuncionarioDTO;
import com.api.estoque.model.funcionario.Funcionario;
import com.api.estoque.model.funcionario.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Funcionario registrarFuncionario(FuncionarioDTO dto) {
            
        String usernameSanitizado = Jsoup.clean(dto.getUsername(), Safelist.none());
        String emailSanitizado = Jsoup.clean(dto.getEmail(), Safelist.none());
        String nomeSanitizado = Jsoup.clean(dto.getNome(), Safelist.none());
        String cargoSanitizado = Jsoup.clean(dto.getCargo(), Safelist.none());
        

        Funcionario funcionario = new Funcionario();
        funcionario.setUsername(usernameSanitizado);
        funcionario.setNome(nomeSanitizado);
        funcionario.setCargo(cargoSanitizado);
        funcionario.setEmail(emailSanitizado);
        funcionario.setPassword(passwordEncoder.encode(dto.getPassword()));

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        return funcionarioSalvo;
        
    }
}
