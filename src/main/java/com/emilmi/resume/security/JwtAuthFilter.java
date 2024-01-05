package com.emilmi.resume.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String TOKEN_HEADER;
    private final UserRepository userRepository;
    private final JwtTokenUtil tokenUtil;

    public JwtAuthFilter(UserRepository userRepository, JwtTokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getServletPath().contains("/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String header = request.getHeader(TOKEN_HEADER);

        if (header == null || !header.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring("Bearer ".length());
        String email = tokenUtil.extractUsername(token);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = this.userRepository.findByEmail(email)
                    .orElse(null);


            if (user == null) {
                filterChain.doFilter(request, response);
                return;
            }

            if (!user.getToken().equals(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            if (tokenUtil.isTokenValid(token, user)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user, null, null
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
