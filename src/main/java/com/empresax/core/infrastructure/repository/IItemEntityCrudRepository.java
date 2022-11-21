package com.empresax.core.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresax.core.infrastructure.entity.ItemEntity;

public interface IItemEntityCrudRepository extends JpaRepository<ItemEntity, UUID> {

}