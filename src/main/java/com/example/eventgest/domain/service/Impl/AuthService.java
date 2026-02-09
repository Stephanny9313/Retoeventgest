package com.example.eventgest.domain.service.Impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager,
                       CustomUserDetailService userDetailsService,
                       JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            return jwtUtil.generateToken(userDetails);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }
    }
}

