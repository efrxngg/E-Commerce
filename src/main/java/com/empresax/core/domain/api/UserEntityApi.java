package com.empresax.core.domain.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.empresax.core.infrastructure.entity.UserEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Entity", description = "Only For Admins")
@SecurityRequirement(name = "Bearer Authentication")
public interface UserEntityApi {

    @Operation(summary = "Create a new User")
    @PostMapping
    ResponseEntity<UserEntity> saveUserEntity(UserEntity userEntity);

    @Operation(summary = "Find all Users")
    @GetMapping(value = "/all")
    ResponseEntity<List<UserEntity>> findAllUsersEntity();

    @Operation(summary = "Find by Id UserEntity all Users")
    @GetMapping(value = "/{id}")
    ResponseEntity<UserEntity> findUserEntityById(UUID id);

    @Operation(summary = "Update UserEntity by ID")
    @PutMapping(value = "/{id}")
    ResponseEntity<UserEntity> updateUserEntityById(UserEntity entity);

    @Operation(summary = "Update UserEntity by ID")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteUserEntityById(UUID id);
}