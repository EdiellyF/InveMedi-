package com.br.InveMedi.inveMedi.configs;

import com.br.InveMedi.inveMedi.security.JWTAuthenticationFilter;
import com.br.InveMedi.inveMedi.security.JWTAuthorizationFilter;
import com.br.InveMedi.inveMedi.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration

public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }


    private static final String[] PUBLIC_MATCHES = {
            "/"
    };

    private final String[] PUBLIC_MATCHES_POST = {
            "/user",
            "/login"
    };


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/login", "/register").permitAll() // Permite acesso sem autenticação
                .anyRequest().authenticated() // Requer autenticação para todas as outras rotas
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(http), jwtUtil))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(http), jwtUtil, this.userDetailsService))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // API sem sessão



        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }



}