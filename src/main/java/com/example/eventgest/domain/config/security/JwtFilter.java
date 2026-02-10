package com.example.eventgest.domain.config.security;

import com.example.eventgest.domain.service.Impl.CustomUserDetailService;
import com.example.eventgest.domain.service.Impl.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;
    private final CustomUserDetailService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String uri = request.getRequestURI();
        String method = request.getMethod();

        // 🔹 ENDPOINTS PÚBLICOS → IGNORAR JWT COMPLETAMENTE
        if (
                uri.startsWith("/api/auth") ||
                        (uri.startsWith("/api/events") && (method.equals("GET") || method.equals("POST"))) ||
                        uri.startsWith("/api/programs") ||
                        uri.startsWith("/api/event-types") ||
                        uri.startsWith("/api/participants") ||
                        uri.startsWith("/api/registrations") ||
                        uri.startsWith("/api/dashboard")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔹 SOLO AQUÍ SE PROCESA JWT
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String username = jwtUtil.extractUsername(token);

            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            logger.debug("JWT inválido o expirado");
        }

        filterChain.doFilter(request, response);
    }
}