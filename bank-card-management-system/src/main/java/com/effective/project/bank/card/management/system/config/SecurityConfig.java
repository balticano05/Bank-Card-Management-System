package com.effective.project.bank.card.management.system.config;

import com.effective.project.bank.card.management.system.exception.type.SecurityConfigurationException;
import com.effective.project.bank.card.management.system.security.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter authFilter;
    private final AuthenticationConfiguration authConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() {
        try {
            return authConfiguration.getAuthenticationManager();
        } catch (Exception e) {
            throw new SecurityConfigurationException("Failed to configure authentication manager: " + e.getMessage());
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

        try {
            httpSecurity
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authFilter -> {

                        authFilter.requestMatchers(
                                "/api/v1/auth/register",
                                "/api/v1/auth/authenticate"
                        ).permitAll();

                    }).authorizeHttpRequests(authFilter -> {
                        authFilter
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAnyRole("ADMIN", "USER");

                    })
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

            return httpSecurity.build();
        } catch (Exception e) {
            throw new SecurityConfigurationException("Failed to configure security: " + e.getMessage());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
