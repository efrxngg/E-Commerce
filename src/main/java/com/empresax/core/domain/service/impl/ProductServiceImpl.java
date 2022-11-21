package com.empresax.core.domain.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.empresax.core.domain.model.Product;
import com.empresax.core.domain.repository.IProductRepository;
import com.empresax.core.domain.service.IProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private IProductRepository productRepository;

    @Override
    public Product findProductById(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

}
