package com.empresax.core.domain.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.empresax.core.domain.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "End Points Avalible to User")
public interface UserApi {

    @Operation(summary = "Create a new User", description = "")
    @PostMapping(value = "/add")
    ResponseEntity<User> createUser(User User);

    @Operation(summary = "Update User by Id")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping
    ResponseEntity<User> updateUserById(User User, HttpServletRequest request);

    @Operation(summary = "Delete User by Id")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping
    ResponseEntity<Void> deleteUserById(HttpServletRequest request);

}