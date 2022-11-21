package com.empresax.core.application.rest.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.application.security.jwt.JwtUtil;
import com.empresax.core.domain.api.AuthenticationApi;
import com.empresax.core.domain.model.dto.UserAuthentication;
import com.empresax.core.domain.service.impl.UserDetailsImpl;
import com.empresax.core.infrastructure.entity.TokenEntity;
import com.empresax.core.infrastructure.repository.ITokenRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthRestController implements AuthenticationApi {


    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private ITokenRepository tokenRepository;

    @Override
    public ResponseEntity<String> signIn(@Valid @RequestBody UserAuthentication user) {
        var au = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String token = jwtUtil.create((UserDetailsImpl) au.getPrincipal());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> signOut(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        if (tokenRepository.existsByValue(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        tokenRepository.save(new TokenEntity(null, token));
        return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
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