package com.empresax.core.application.rest.controller.user;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.domain.api.ProductApi;
import com.empresax.core.domain.model.Product;
import com.empresax.core.domain.service.IProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/products")
@AllArgsConstructor
public class ProductRestController implements ProductApi {

    private IProductService productService;
    
    @Override
    public ResponseEntity<List<Product>> findProductById() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> findProductById(@PathVariable UUID id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }
    
}