package com.abdellah.hospitalapp.security;

import com.abdellah.hospitalapp.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    UserDetailsServiceImpl userDetailsService;


    // THIS IS A JDBC USER DETAILS AUTHENTICATION
    // @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }


    // THIS IS IN MEMORY AUTHENTICATION
    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").
                        password(passwordEncoder().encode("12345678")).roles("USER").build(),
                User.withUsername("user2").
                        password(passwordEncoder().encode("12345678")).roles("USER").build(),
                User.withUsername("admin").
                        password(passwordEncoder().encode("12345678")).roles("USER","ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(login->login.loginPage("/login").defaultSuccessUrl("/",true).permitAll())
                .rememberMe(remember ->
                        remember.rememberMeParameter("remember-me"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/webjars/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .exceptionHandling(ex -> ex.accessDeniedPage("/notAuthorized"))
                .build();
    }



    // We add @Bean to the passwordEncoder() method so that Spring can manage it
    // and make it available for dependency injection anywhere in the app.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
