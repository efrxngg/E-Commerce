package com.empresax.core.domain.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.empresax.core.domain.model.dto.UserAuthentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Primary
@Tag(name = "Authentication", description = "Authentication required for other endpoints")
public interface AuthenticationApi {

    @Operation(summary = "Sign In")
    @PostMapping(value = "/in", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String> signIn(UserAuthentication user);

    @Operation(summary = "Sign Out")
    @GetMapping("/out")
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<Void> signOut(HttpServletRequest request);

}