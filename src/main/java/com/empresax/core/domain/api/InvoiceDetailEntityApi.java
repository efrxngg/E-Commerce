package com.empresax.core.domain.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.empresax.core.infrastructure.entity.InvoiceDetailEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Invoice Detail Entity", description = "Only For Admins")
@SecurityRequirement(name = "Bearer Authentication")
public interface InvoiceDetailEntityApi {

    @Operation(summary = "Create a new Invoice Detail")
    @PostMapping
    ResponseEntity<InvoiceDetailEntity> saveInvoiceDetailEntity(InvoiceDetailEntity entity);

    @Operation(summary = "Find all Invoices Detail")
    @GetMapping(value = "/all")
    ResponseEntity<List<InvoiceDetailEntity>> findAllInvoiceDetailEntity();

    @Operation(summary = "Find by Id Invoice Detail")
    @GetMapping(value = "/{id}")
    ResponseEntity<InvoiceDetailEntity> findInvoiceDetailEntityById(UUID id);

    @Operation(summary = "Update InvoiceDetailEntity by ID")
    @PutMapping(value = "/{id}")
    ResponseEntity<InvoiceDetailEntity> updateInvoiceDetailEntityById(InvoiceDetailEntity entity);

    @Operation(summary = "Update InvoiceDetailEntity by ID")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteInvoiceDetailEntityById(UUID id);
}