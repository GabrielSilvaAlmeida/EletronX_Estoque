package com.api.estoque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.api.estoque.model.funcionario.Funcionario;
import com.api.estoque.model.funcionario.FuncionarioRepository;
import java.util.ArrayList;
@Component
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private FuncionarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Funcionario user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
    return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
    
}
