package com.api.estoque.model.funcionario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.estoque.model.historico.Historico;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "Funcionarios")
@Entity(name = "Funcionario")
public class Funcionario  implements UserDetails {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;
 
    @Column(nullable = false)
    private String nome;

    
    @Column(nullable = false)
    private String cargo;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Historico> historicoFuncionario;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false)
    private String email;

    public enum Role {
        USER,
        ADMIN,
        FUNCIONARIO,
        ESTOQUE,
        CEO
    }
    
    @Enumerated(EnumType.STRING)
    private Role role = Role.FUNCIONARIO;




    public Funcionario(DadosFuncionario dados) {
        this.nome = dados.nome();
        this.cargo = dados.cargo();
        this.password = dados.password();
        this.email = dados.email();
    }

    public void atualizarFuncionario(DadosAtualizarFuncionario dados) {
        if(dados.password() != null) {
            this.password = dados.password();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "ROLE_" + role.name());
    }

    @Override
    public String getPassword() {
        return password;
    }



    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
