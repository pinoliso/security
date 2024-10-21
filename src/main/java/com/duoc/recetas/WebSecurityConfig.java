package com.duoc.recetas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Description;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/recipes").permitAll()
                        .requestMatchers("/**.css").permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    @Description("In memory Userdetails service registered since DB doesn't have user table ")
    UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        UserDetails epino = User.builder()
                .username("epino")
                .password(passwordEncoder().encode("epino123"))
                .roles("USER")
                .build();
        UserDetails schavez = User.builder()
                .username("schavez")
                .password(passwordEncoder().encode("schavez123"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin123"))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(epino, schavez, admin);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}