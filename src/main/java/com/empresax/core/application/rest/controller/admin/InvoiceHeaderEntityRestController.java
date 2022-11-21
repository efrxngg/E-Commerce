package com.empresax.core.application.rest.controller.admin;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.domain.api.InvoiceHeaderEntityApi;
import com.empresax.core.infrastructure.entity.InvoiceHeaderEntity;
import com.empresax.core.infrastructure.repository.IInvoiceHeaderEntityCrudRepository;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;

@Hidden
@RestController
@RequestMapping(value = "/api/v1/admin/invoice-header")
@AllArgsConstructor
public class InvoiceHeaderEntityRestController implements InvoiceHeaderEntityApi {

    private IInvoiceHeaderEntityCrudRepository invoiceHeaderEntityCrudRepository;

    @Override
    public ResponseEntity<InvoiceHeaderEntity> saveInvoiceHeaderEntity(@RequestBody InvoiceHeaderEntity entity) {

        return new ResponseEntity<>(invoiceHeaderEntityCrudRepository.save(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<InvoiceHeaderEntity>> findAllInvoiceHeaderEntity() {
        return new ResponseEntity<>(invoiceHeaderEntityCrudRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceHeaderEntity> findInvoiceHeaderEntityById(@PathVariable UUID id) {
        return new ResponseEntity<>(invoiceHeaderEntityCrudRepository.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceHeaderEntity> updateInvoiceHeaderEntityById(@RequestBody InvoiceHeaderEntity entity) {
        return new ResponseEntity<>(invoiceHeaderEntityCrudRepository.save(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteInvoiceHeaderEntityById(@PathVariable UUID id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}