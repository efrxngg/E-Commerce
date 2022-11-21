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

import com.empresax.core.domain.api.ProdutEntityApi;
import com.empresax.core.infrastructure.entity.ProductEntity;
import com.empresax.core.infrastructure.repository.IProductEntityCrudRepository;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;

@Hidden
@RestController
@RequestMapping(value = "/api/v1/admin/products")
@AllArgsConstructor
public class ProductEntityRestController implements ProdutEntityApi {

    private IProductEntityCrudRepository productRepository;

    @Override
    public ResponseEntity<ProductEntity> saveProductEntity(@Valid @RequestBody ProductEntity productEntity) {
        return new ResponseEntity<>(productRepository.save(productEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ProductEntity>> findAllProductsEntity() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ProductEntity> findProductEntityById(@PathVariable UUID id) {
        return new ResponseEntity<>(productRepository.findById(id).get(), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<ProductEntity> updateProductEntityById(@Valid @RequestBody ProductEntity entity) {
        return new ResponseEntity<>(productRepository.save(entity), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Void> deleteProductEntityById(UUID id) {
        // productRepository.save(id);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}