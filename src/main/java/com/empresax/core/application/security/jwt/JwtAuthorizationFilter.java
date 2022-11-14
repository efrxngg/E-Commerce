package com.empresax.core.application.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.empresax.core.infrastructure.repository.ITokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import static com.empresax.core.application.security.Constant.ROLE_CLAIM;
import static com.empresax.core.application.security.Constant.TOKEN_PREFIX;;

// 1 Authorization
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ITokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (Objects.isNull(header) || !header.startsWith(TOKEN_PREFIX)
                || tokenRepository.findByValue(header.replace(TOKEN_PREFIX, "")).isPresent()) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.replace(TOKEN_PREFIX, "");
        Claims claims = null;
        try {
            claims = jwtUtil.getClaims(token);
        } catch (ExpiredJwtException e) {
        }
        if (Objects.nonNull(claims)) {
            var user = createUsernamePasswordAuthenticationToken(claims);
            SecurityContextHolder.getContext().setAuthentication(user);
        }
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken createUsernamePasswordAuthenticationToken(Claims claims) {
        var user = new UsernamePasswordAuthenticationToken(
                claims.get("sub", String.class),
                null,
                List.of(claims.get(ROLE_CLAIM).toString().split(",")).stream()
                        .map(rol -> rol.replace("[", "").replace("]", "").strip())
                        .map(rol -> new SimpleGrantedAuthority(rol))
                        .collect(Collectors.toList()));
        return user;
    }

}