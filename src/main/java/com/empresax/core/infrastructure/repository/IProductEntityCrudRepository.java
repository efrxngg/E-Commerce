package com.empresax.core.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresax.core.infrastructure.entity.ProductEntity;

public interface IProductEntityCrudRepository extends JpaRepository<ProductEntity, UUID> {
}