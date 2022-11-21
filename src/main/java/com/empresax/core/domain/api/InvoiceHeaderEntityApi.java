package com.empresax.core.domain.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.empresax.core.infrastructure.entity.InvoiceHeaderEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Invoice Header Entity", description = "Only For Admins")
@SecurityRequirement(name = "Bearer Authentication")
public interface InvoiceHeaderEntityApi {

    @Operation(summary = "Create a new Cart")
    @PostMapping
    ResponseEntity<InvoiceHeaderEntity> saveInvoiceHeaderEntity(InvoiceHeaderEntity entity);

    @Operation(summary = "Find all Invoices Header")
    @GetMapping(value = "/all")
    ResponseEntity<List<InvoiceHeaderEntity>> findAllInvoiceHeaderEntity();

    @Operation(summary = "Find by Id Invoice Header")
    @GetMapping(value = "/{id}")
    ResponseEntity<InvoiceHeaderEntity> findInvoiceHeaderEntityById(UUID id);

    @Operation(summary = "Update InvoiceHeaderEntity by ID")
    @PutMapping(value = "/{id}")
    ResponseEntity<InvoiceHeaderEntity> updateInvoiceHeaderEntityById(InvoiceHeaderEntity entity);

    @Operation(summary = "Update InvoiceHeaderEntity by ID")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteInvoiceHeaderEntityById(UUID id);
}