package com.example.eventgest.domain.config.security;

import com.example.eventgest.domain.service.Impl.CustomUserDetailService;
import com.example.eventgest.domain.service.Impl.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;



import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String uri = request.getRequestURI();

        // ✅ Ignorar endpoints públicos
        if (uri.startsWith("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // NO bloquear aquí, dejar que Spring decida
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        // TODO: validar token y setear SecurityContext
        // SecurityContextHolder.getContext().setAuthentication(...);

        filterChain.doFilter(request, response);
    }
}


