package com.empresax.core.application.rest.controller.admin;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import io.swagger.v3.oas.annotations.Hidden;

import com.empresax.core.domain.api.CartEntityApi;
import com.empresax.core.infrastructure.entity.CartEntity;
import com.empresax.core.infrastructure.repository.ICartEntityCrudRepository;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;

@Hidden
@RestController
@RequestMapping(value = "/api/v1/admin/carts")
@AllArgsConstructor
public class CartEntityRestController implements CartEntityApi {

    private ICartEntityCrudRepository cartEntityCrudRepository;

    @Override
    public ResponseEntity<CartEntity> saveCartEntity(@Valid @RequestBody CartEntity cartEntity) {
        return new ResponseEntity<>(cartEntityCrudRepository.save(cartEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CartEntity>> findAllCartsEntity() {
        return new ResponseEntity<>(cartEntityCrudRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartEntity> findCartEntityById(@PathVariable UUID id) {
        return new ResponseEntity<>(cartEntityCrudRepository.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartEntity> updateCartEntityById(@Valid @RequestBody CartEntity entity) {
        return new ResponseEntity<>(cartEntityCrudRepository.save(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteCartEntityById(@PathVariable UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}