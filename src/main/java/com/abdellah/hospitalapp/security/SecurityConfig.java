package com.abdellah.hospitalapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        System.out.println(passwordEncoder().encode("1234"));
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").
                        password(passwordEncoder().encode("12345678")).roles("USER").build(),
                User.withUsername("user2").
                        password(passwordEncoder().encode("12345678")).roles("USER").build(),
                User.withUsername("admin").
                        password(passwordEncoder().encode("12345678")).roles("ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.formLogin(Customizer.withDefaults()).
        authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers("/deletePatient/**").hasRole("ADMIN"))
        .authorizeHttpRequests(
                authorizeRequests -> authorizeRequests.anyRequest().authenticated()).build();
    }


    // We add @Bean to the passwordEncoder() method so that Spring can manage it
    // and make it available for dependency injection anywhere in the app.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
