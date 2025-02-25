package com.userServiceAuth.config;

import com.userServiceAuth.utility.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;


    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;

    }

   /* @Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //  Correct way in Spring Security 6.1+
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }*/

/*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/public").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(withDefaults()); // Enables Okta OAuth 2.0 login

        return http.build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/public").permitAll()  // Public endpoints
                        .requestMatchers("/api/user").authenticated() // OAuth2 Login required
                        .requestMatchers("/api/protected").authenticated() // JWT required
                )
                .oauth2Login(withDefaults())  // OAuth2 Login support
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())); // Enable JWT auth

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
