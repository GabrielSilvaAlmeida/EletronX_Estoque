package com.api.estoque.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.api.estoque.filter.RateLimitFilter;

import jakarta.servlet.Filter;



import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .ignoringRequestMatchers("/api/estoque/registro-funcionario", "/api/estoque/produto/registro-produto")
            )
            
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/estoque/registro").permitAll()
                .requestMatchers("/api/estoque/**").permitAll()
                .anyRequest().permitAll()
                )
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable());

            return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "rateLimitingFilterBean")
    public FilterRegistrationBean<Filter> rateLimitingFilter(RateLimitFilter filter) {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/api/estoque/registro-funcionario");
        return registrationBean;
    }
}
