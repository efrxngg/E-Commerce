package com.empresax.core.application.rest.controller.user;

import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.application.security.jwt.JwtUtil;
import com.empresax.core.domain.api.InvoiceApi;
import com.empresax.core.domain.model.Invoice;
import com.empresax.core.domain.model.InvoiceDetail;
import com.empresax.core.domain.service.IInvoiceService;
import com.empresax.core.infrastructure.entity.StateType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/invoices")
@AllArgsConstructor
public class InvoiceRestController implements InvoiceApi {

    private IInvoiceService invoiceService;
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> createInvoice(HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        var invoice = invoiceService.createInvoice(UUID.fromString(claim.get("id", String.class)));

        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<Invoice>> findAllInvoice(HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        var invoices = invoiceService.findAllInvoiceByUserId(UUID.fromString(claim.get("id", String.class)));

        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Invoice> findInvoiceById(@PathVariable UUID id, HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        var invoice = invoiceService.findInvoiceById(id, UUID.fromString(claim.get("id", String.class)));

        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @Override
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

    @Override
    public ResponseEntity<InvoiceDetail> changeQuantityInvoiceDetailt(@RequestBody InvoiceDetail invoiceDetail,
            @PathVariable UUID id_invoice, HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        var id_user = UUID.fromString(claim.get("id", String.class));

        var result = invoiceService.changeQuantityInvoiceDetailt(invoiceDetail, id_invoice, id_user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
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