package com.empresax.core.application.rest.controller.user;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.application.security.jwt.JwtUtil;
import com.empresax.core.domain.api.CartApi;
import com.empresax.core.domain.model.Cart;
import com.empresax.core.domain.model.dto.CartCreate;
import com.empresax.core.domain.model.dto.ItemUpdate;
import com.empresax.core.domain.service.ICartService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/cart")
@AllArgsConstructor
public class CartRestController implements CartApi {

    private ICartService cartService;
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<CartCreate> createCart(@RequestBody CartCreate entity, HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        entity.setId_user(UUID.fromString(claim.get("id", String.class)));
        return new ResponseEntity<>(cartService.saveOrUpdateCart(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cart> getCart(HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        return new ResponseEntity<>(cartService.getCartByUserId(UUID.fromString(claim.get("id", String.class))),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemUpdate> updateItemCart(@RequestBody ItemUpdate item) {
        return new ResponseEntity<>(cartService.updateQuantityByItemId(item), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> cleanCart(HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        cartService.cleanCartByUserId(UUID.fromString(claim.get("id", String.class)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}