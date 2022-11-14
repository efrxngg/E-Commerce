package com.empresax.core.domain.api;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.empresax.core.domain.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "Actions available to users")
public interface UserApi {

    @Operation(summary = "Create a new User")
    @PostMapping(value = "/add")
    ResponseEntity<User> createUser(User User);

    @Operation(summary = "Update User by Id")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping
    ResponseEntity<User> updateUserById(User User);

    @Operation(summary = "Delete User by Id")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteUserById(UUID id);

}