package com.api.estoque.controller;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.estoque.model.dto.LoginRequestDTO;
import com.api.estoque.model.dto.RegisterRequestDTO;
import com.api.estoque.model.dto.ResponseDTO;
import com.api.estoque.model.funcionario.Funcionario;
import com.api.estoque.model.funcionario.FuncionarioRepository;
import com.api.estoque.security.TokenService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final FuncionarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
    Funcionario user = this.repository.findByEmail(body.email())
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (passwordEncoder.matches(body.password(), user.getPassword())) {
        String token = this.tokenService.generateToken(user);

        // Criando o cookie HTTP-Only
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", token)
            .httpOnly(true)   // Protege contra XSS
            .secure(true)     // Só envia com HTTPS (em produção)
            .path("/")        // Disponível para toda a aplicação
            .maxAge(Duration.ofHours(2)) // Tempo de expiração
            .build();

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, jwtCookie.toString()) // Enviando o cookie
            .body(Map.of("message", "Login successful", "user", user.getNome()));
    }

    return ResponseEntity.badRequest().build();
}


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<Funcionario> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            Funcionario newUser = new Funcionario();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setCargo(body.cargo());
            newUser.setEmail(body.email());
            newUser.setNome(body.nome());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}