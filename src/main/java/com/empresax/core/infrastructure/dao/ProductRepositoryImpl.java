package com.empresax.core.infrastructure.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.empresax.core.domain.model.Product;
import com.empresax.core.domain.repository.IProductRepository;
import com.empresax.core.infrastructure.config.Mapper;
import com.empresax.core.infrastructure.repository.IProductEntityCrudRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements IProductRepository {

    @NonNull
    private IProductEntityCrudRepository productEntityCrudRepository;
    @NonNull
    private Mapper mapper;

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.of(mapper.to(productEntityCrudRepository.findById(id).get()));
    }

    @Override
    public List<Product> findAll() {
        return productEntityCrudRepository.findAll().stream()
                .map(mapper::to)
                .collect(Collectors.toList());
    }

}