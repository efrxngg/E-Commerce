package com.empresax.core.domain.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.empresax.core.domain.model.Cart;
import com.empresax.core.domain.model.dto.CartCreate;
import com.empresax.core.domain.model.dto.ItemUpdate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cart", description = "End Points Avalible to User")
@SecurityRequirement(name = "Bearer Authentication")
public interface CartApi {

    @Operation(summary = "Save Or Update Cart", description = "A user can only have to one cart, no need to pass the user id or the cart id")
    @PostMapping
    ResponseEntity<CartCreate> createCart(CartCreate entity, HttpServletRequest request);

    @Operation(summary = "Get Cart")
    @GetMapping
    ResponseEntity<Cart> getCart(HttpServletRequest request);
    
    @Operation(summary = "Change Item Quantity")
    @PutMapping
    ResponseEntity<ItemUpdate> updateItemCart(ItemUpdate item);

    @Operation(summary = "Clean Items From Cart")
    @DeleteMapping 
    ResponseEntity<?> cleanCart(HttpServletRequest request);
}