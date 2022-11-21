package com.empresax.core.domain.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.empresax.core.infrastructure.entity.CartEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cart Entity", description = "Only For Admins")
@SecurityRequirement(name = "Bearer Authentication")
public interface CartEntityApi {

    @Operation(summary = "Create a new Cart")
    @PostMapping
    ResponseEntity<CartEntity> saveCartEntity(CartEntity cartEntity);

    @Operation(summary = "Find all Carts")
    @GetMapping(value = "/all")
    ResponseEntity<List<CartEntity>> findAllCartsEntity();

    @Operation(summary = "Find by Id CartEntity all Carts")
    @GetMapping(value = "/{id}")
    ResponseEntity<CartEntity> findCartEntityById(UUID id);

    @Operation(summary = "Update CartEntity by ID")
    @PutMapping(value = "/{id}")
    ResponseEntity<CartEntity> updateCartEntityById(CartEntity entity);

    @Operation(summary = "Update CartEntity by ID")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteCartEntityById(UUID id);
}