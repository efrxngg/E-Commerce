package com.empresax.core.domain.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.empresax.core.infrastructure.entity.ProductEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product Entity", description = "Only For Admins")
@SecurityRequirement(name = "Bearer Authentication")
public interface ProdutEntityApi {

    @Operation(summary = "Create a new Product", description = "First the image must be added and then the returned id must be passed as the path of the image to be saved. <a href='#'>Media Service</a>")
    @PostMapping
    ResponseEntity<ProductEntity> saveProductEntity(ProductEntity ProductEntity);

    @Operation(summary = "Find all Products")
    @GetMapping(value = "/all")
    ResponseEntity<List<ProductEntity>> findAllProductsEntity();

    @Operation(summary = "Find by Id ProductEntity all Products")
    @GetMapping(value = "/{id}")
    ResponseEntity<ProductEntity> findProductEntityById(UUID id);

    @Operation(summary = "Update ProductEntity by ID")
    @PutMapping(value = "/{id}")
    ResponseEntity<ProductEntity> updateProductEntityById(ProductEntity entity);

    @Operation(summary = "Update ProductEntity by ID")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteProductEntityById(UUID id);
}