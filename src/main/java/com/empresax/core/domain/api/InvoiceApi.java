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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Invoice", description = "End Points Avalible to User")
@SecurityRequirement(name = "Bearer Authentication")
public interface InvoiceApi {

    @PostMapping
    ResponseEntity<?> createInvoice(HttpServletRequest request);

    @GetMapping(value = "/all")
    ResponseEntity<Set<Invoice>> findAllInvoice(HttpServletRequest request);

    @GetMapping(value = "/{id}")
    ResponseEntity<Invoice> findInvoiceById(UUID id, HttpServletRequest request);

    @PutMapping(value = "/hea/{id_invoice}/det/{id_invoice_det}/state/{state}")
    public ResponseEntity<InvoiceDetail> changeStateInvoiceDetailt(
            UUID id_invoice_det,
            UUID id_invoice,
            StateType state,
            HttpServletRequest request);

    @PutMapping(value = "/det/{id_invoice}/quantity")
    public ResponseEntity<InvoiceDetail> changeQuantityInvoiceDetailt(
            InvoiceDetail invoiceDetail,
            UUID id_invoice,
            HttpServletRequest request);

    @PutMapping(value = "/hea/{id_invoice}/state/{state}")
    public ResponseEntity<Void> changeStateInvoiceHeader(
            UUID id_invoice,
            StateType state,
            HttpServletRequest request);

}