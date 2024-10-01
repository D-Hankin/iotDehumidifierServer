package com.iotdehumidifier.iotdehumidifier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.iotdehumidifier.iotdehumidifier.services.JpaUserDetailService;

@Configuration
@EnableWebSecurity
public class SecConfig {


    private final JpaUserDetailService jpaUserDetailService;
    
    public SecConfig(JpaUserDetailService jpaUserDetailService) {
        this.jpaUserDetailService = jpaUserDetailService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
        .requestMatchers("/create-account")
        .permitAll()
        .anyRequest()
        .authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .permitAll())
        .userDetailsService(jpaUserDetailService)
        .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(jpaUserDetailService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}

