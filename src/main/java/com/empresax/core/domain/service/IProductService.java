package com.empresax.core.domain.service;

import java.util.List;
import java.util.UUID;

import com.empresax.core.domain.model.Product;

public interface IProductService {

    Product findProductById(UUID id);

    List<Product> findAllProducts();

}