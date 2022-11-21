package com.empresax.core.application.rest.controller.admin;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.domain.api.InvoiceDetailEntityApi;
import com.empresax.core.infrastructure.entity.InvoiceDetailEntity;
import com.empresax.core.infrastructure.repository.InvoiceDetailEntityCrudRepository;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;

@Hidden
@RestController
@RequestMapping(value = "/api/v1/admin/invoice-detail")
@AllArgsConstructor
public class InvoiceDetialEntityRestController implements InvoiceDetailEntityApi {

    private InvoiceDetailEntityCrudRepository invoiceDetailEntityCrudRepository;

    @Override
    public ResponseEntity<InvoiceDetailEntity> saveInvoiceDetailEntity(@RequestBody InvoiceDetailEntity entity) {
        return new ResponseEntity<>(invoiceDetailEntityCrudRepository.save(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<InvoiceDetailEntity>> findAllInvoiceDetailEntity() {
        return new ResponseEntity<>(invoiceDetailEntityCrudRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceDetailEntity> findInvoiceDetailEntityById(@PathVariable UUID id) {
        return new ResponseEntity<>(invoiceDetailEntityCrudRepository.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceDetailEntity> updateInvoiceDetailEntityById(@RequestBody InvoiceDetailEntity entity) {
        return new ResponseEntity<>(invoiceDetailEntityCrudRepository.save(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteInvoiceDetailEntityById(@PathVariable UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}