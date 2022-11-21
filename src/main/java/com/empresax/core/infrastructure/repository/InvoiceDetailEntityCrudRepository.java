package com.empresax.core.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresax.core.infrastructure.entity.InvoiceDetailEntity;

public interface InvoiceDetailEntityCrudRepository extends JpaRepository<InvoiceDetailEntity, UUID> {

}
