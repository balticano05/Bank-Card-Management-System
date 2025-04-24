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

            log.info("Authorization header: {}", authHeader);

            final String token;
            String login;

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {

                log.warn("No Bearer token found in Authorization header");

                filterChain.doFilter(request, response);
                return;
            }

            token = authHeader.substring(7);
            login = jwtService.extractLogin(token);

            log.info("Extracted login from token: {}", login);

            if (login == null) {

                log.error("Invalid token structure - no login extracted");

                throw new AuthenticationFailedException("Invalid token structure");
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(login);

                log.info("Loaded user details with authorities: {}", userDetails.getAuthorities());

                if (!jwtService.validateToken(token, userDetails)) {

                    log.error("Token validation failed for user: {}", login);

                    throw new AuthenticationFailedException("Token validation failed");
                }

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                log.info("Created authentication token with authorities: {}", authenticationToken.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                log.info("Authentication set in SecurityContext");
            }

            filterChain.doFilter(request, response);

        } catch (MissingTokenException | AuthenticationFailedException e) {

            throw e;
        } catch (IOException e) {

            throw new CustomIOException("I/O error occurred in jwt filter: " + e.getMessage());
        } catch (ServletException e) {

            throw new CustomServletException("Servlet error occurred in jwt filter: " + e.getMessage());
        }

    }

}
