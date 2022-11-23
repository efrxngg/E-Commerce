package com.empresax.core.domain.service;

import java.util.Set;
import java.util.UUID;

import com.empresax.core.domain.model.Invoice;
import com.empresax.core.domain.model.InvoiceDetail;
import com.empresax.core.infrastructure.entity.StateType;

public interface IInvoiceService {

    Invoice createInvoice(UUID id_user);

    Set<Invoice> findAllInvoiceByUserId(UUID id_user);

    Invoice findInvoiceById(UUID id_invoice, UUID id_user);

    void changeStateInvoiceDetailt(UUID idInvoiceDet, StateType state, UUID id_invoice, UUID id_user);

    InvoiceDetail changeQuantityInvoiceDetailt(InvoiceDetail invoiceDet, UUID id_invoice, UUID id_user);

    void changeStateInvoiceHeader(UUID id_user, UUID id_invoice, StateType state);
}