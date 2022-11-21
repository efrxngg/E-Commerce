package com.empresax.core.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.empresax.core.domain.model.Product;

public interface IProductRepository {

    Optional<Product> findById(UUID id);

    List<Product> findAll();
    
}