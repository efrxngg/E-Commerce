package com.empresax.core.domain.api;

import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.empresax.core.domain.model.Invoice;
import com.empresax.core.domain.model.InvoiceDetail;
import com.empresax.core.infrastructure.entity.StateType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Invoice", description = "End Points Avalible to User")
@SecurityRequirement(name = "Bearer Authentication")
public interface InvoiceApi {

    @Operation(summary = "Generate invoice from cart")
    @PostMapping
    ResponseEntity<?> createInvoice(HttpServletRequest request);

    @Operation(summary = "Obtain all invoices for user")
    @GetMapping(value = "/all")
    ResponseEntity<Set<Invoice>> findAllInvoice(HttpServletRequest request);

    @Operation(summary = "Find invoice by id invoice")
    @GetMapping(value = "/{id}")
    ResponseEntity<Invoice> findInvoiceById(UUID id, HttpServletRequest request);

    @Operation(summary = "Change invoice item status ")
    @PutMapping(value = "/hea/{id_invoice}/det/{id_invoice_det}/state/{state}")
    public ResponseEntity<InvoiceDetail> changeStateInvoiceDetailt(
            UUID id_invoice_det,
            UUID id_invoice,
            StateType state,
            HttpServletRequest request);

    @Operation(summary = "Change invoice item quantity")
    @PutMapping(value = "/det/{id_invoice}/quantity")
    public ResponseEntity<InvoiceDetail> changeQuantityInvoiceDetailt(
            InvoiceDetail invoiceDetail,
            UUID id_invoice,
            HttpServletRequest request);

    @Operation(summary = "Change Invoice status ")
    @PutMapping(value = "/hea/{id_invoice}/state/{state}")
    public ResponseEntity<Void> changeStateInvoiceHeader(
            UUID id_invoice,
            StateType state,
            HttpServletRequest request);

}