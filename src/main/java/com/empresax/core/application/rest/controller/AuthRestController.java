package com.empresax.core.application.rest.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.application.security.jwt.JwtUtil;
import com.empresax.core.domain.api.AuthenticationApi;
import com.empresax.core.domain.model.dto.UserAuthentication;
import com.empresax.core.infrastructure.entity.TokenEntity;
import com.empresax.core.infrastructure.repository.ITokenRepository;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController implements AuthenticationApi {

    private Logger logger = LoggerFactory.getLogger(AuthRestController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ITokenRepository tokenRepository;

    @Override
    public ResponseEntity<String> signIn(@Valid @RequestBody UserAuthentication user) {
        var au = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String token = jwtUtil.create((UserDetails) au.getPrincipal());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> signOut(HttpServletRequest request) {
        String header = null;
        try {
            header = request.getHeader("Authorization");
        } catch (Exception e) {
            logger.error("Error in the header", e);
        }
        String token = Objects.nonNull(header) ? header.replace("Bearer ", "") : null;

        if (Objects.isNull(token) || tokenRepository.existsByValue(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            
        tokenRepository.save(new TokenEntity(null, token));
        return new ResponseEntity<>(HttpStatus.PERMANENT_REDIRECT);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String globalValidExveption(MethodArgumentNotValidException e) {
        StringBuilder msg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> msg.append(error.getDefaultMessage()).append(", "));
        int len = msg.length();
        msg.delete(len - 2, len);
        return msg.toString();
    }

}