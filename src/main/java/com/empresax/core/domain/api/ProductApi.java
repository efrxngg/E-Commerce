package com.empresax.core.domain.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.empresax.core.domain.model.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product", description = "End Points Avalible to User")
public interface ProductApi {
    
    @Operation(summary = "Find all products", description = "")
    @GetMapping
    ResponseEntity<List<Product>> findProductById();

    @Operation(summary = "Find product by id", description = "")
    @GetMapping(value = "/{id}")
    ResponseEntity<Product> findProductById(UUID id);

}