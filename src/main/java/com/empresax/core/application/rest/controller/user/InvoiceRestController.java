package com.empresax.core.application.rest.controller.user;

import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.application.security.jwt.JwtUtil;
import com.empresax.core.domain.model.Invoice;
import com.empresax.core.domain.model.InvoiceDetail;
import com.empresax.core.domain.service.IInvoiceService;
import com.empresax.core.infrastructure.entity.StateType;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/invoices")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
public class InvoiceRestController {

    private IInvoiceService invoiceService;
    private JwtUtil jwtUtil;

    @PostMapping
    ResponseEntity<?> createInvoice(HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        var invoice = invoiceService.createInvoice(UUID.fromString(claim.get("id", String.class)));

        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Set<Invoice>> findAllInvoice(HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        var invoices = invoiceService.findAllInvoiceByUserId(UUID.fromString(claim.get("id", String.class)));

        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Invoice> findInvoiceById(@PathVariable UUID id, HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        var invoice = invoiceService.findInvoiceById(id, UUID.fromString(claim.get("id", String.class)));

        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PutMapping(value = "/hea/{id_invoice}/det/{id_invoice_det}/state/{state}")
    public ResponseEntity<InvoiceDetail> changeStateInvoiceDetailt(
            @PathVariable UUID id_invoice_det,
            @PathVariable UUID id_invoice,
            @PathVariable StateType state,
            HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        var id_user = UUID.fromString(claim.get("id", String.class));

        invoiceService.changeStateInvoiceDetailt(id_invoice_det, state, id_invoice, id_user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/det/{id_invoice}/quantity")
    public ResponseEntity<InvoiceDetail> changeQuantityInvoiceDetailt(@RequestBody InvoiceDetail invoiceDetail,
            @PathVariable UUID id_invoice, HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        var id_user = UUID.fromString(claim.get("id", String.class));

        var result = invoiceService.changeQuantityInvoiceDetailt(invoiceDetail, id_invoice, id_user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/hea/{id_invoice}/state/{state}")
    public ResponseEntity<Void> changeStateInvoiceHeader(
            @PathVariable UUID id_invoice,
            @PathVariable StateType state,
            HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        var id_user = UUID.fromString(claim.get("id", String.class));

        invoiceService.changeStateInvoiceHeader(id_user, id_invoice, state);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}