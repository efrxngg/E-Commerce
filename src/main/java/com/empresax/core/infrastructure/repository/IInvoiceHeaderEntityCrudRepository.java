package com.empresax.core.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresax.core.infrastructure.entity.InvoiceHeaderEntity;

public interface IInvoiceHeaderEntityCrudRepository extends JpaRepository<InvoiceHeaderEntity, UUID> {

}
