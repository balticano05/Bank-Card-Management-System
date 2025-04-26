package com.effective.project.bank.card.management.system.security.filter;

import com.effective.project.bank.card.management.system.exception.type.AuthenticationFailedException;
import com.effective.project.bank.card.management.system.exception.type.CustomIOException;
import com.effective.project.bank.card.management.system.exception.type.CustomServletException;
import com.effective.project.bank.card.management.system.exception.type.MissingTokenException;
import com.effective.project.bank.card.management.system.security.service.impl.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) {

        try {

            String authHeader = request.getHeader("Authorization");

            final String token;
            String login;

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {

                log.warn("No Bearer token found in request");

                filterChain.doFilter(request, response);
                return;
            }

            token = authHeader.substring(7);
            login = jwtService.extractLogin(token);

            if (login == null) {

                log.error("Invalid token structure");

                throw new AuthenticationFailedException("Invalid token structure");
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                log.info("Validating token for user: {}", login);

                UserDetails userDetails = userDetailsService.loadUserByUsername(login);

                if (!jwtService.validateToken(token, userDetails)) {

                    log.error("Token validation failed for user: {}", login);

                    throw new AuthenticationFailedException("Token validation failed");
                }

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                log.info("User authenticated successfully: {}", login);

            }

            filterChain.doFilter(request, response);

        } catch (MissingTokenException | AuthenticationFailedException e) {

            log.error("Authentication error: {}", e.getMessage(), e);

            throw e;
        } catch (IOException e) {

            log.error("I/O error occurred in JWT filter: {}", e.getMessage(), e);

            throw new CustomIOException("I/O error occurred in jwt filter: " + e.getMessage());
        } catch (ServletException e) {

            log.error("Servlet error occurred in JWT filter: {}", e.getMessage(), e);

            throw new CustomServletException("Servlet error occurred in jwt filter: " + e.getMessage());
        }

    }

}