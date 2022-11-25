package com.empresax.core.domain.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresax.core.domain.exception.InvoiceException;
import com.empresax.core.domain.exception.ItemException;
import com.empresax.core.domain.model.Invoice;
import com.empresax.core.domain.model.InvoiceDetail;
import com.empresax.core.domain.service.IInvoiceService;
import com.empresax.core.infrastructure.config.Mapper;
import com.empresax.core.infrastructure.entity.InvoiceDetailEntity;
import com.empresax.core.infrastructure.entity.InvoiceHeaderEntity;
import com.empresax.core.infrastructure.entity.ItemEntity;
import com.empresax.core.infrastructure.entity.StateType;
import com.empresax.core.infrastructure.repository.ICartEntityCrudRepository;
import com.empresax.core.infrastructure.repository.IInvoiceHeaderEntityCrudRepository;
import com.empresax.core.infrastructure.repository.IUserEntityCrudRepository;
import com.empresax.core.infrastructure.repository.InvoiceDetailEntityCrudRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {

    private IInvoiceHeaderEntityCrudRepository invoiceHeaRepo;
    private InvoiceDetailEntityCrudRepository invoiceDetRepo;
    private ICartEntityCrudRepository cartRepo;
    private IUserEntityCrudRepository userRepo;
    private Mapper mapper;

    private static final BigDecimal IVA = new BigDecimal(0.12);

    @Override
    @Transactional
    public Invoice createInvoice(UUID id_user) {
        var cart = cartRepo.findByUserId(id_user).orElseThrow();
        var cartItems = cart.getItems();
        if (cartItems.isEmpty())
            throw new ItemException("Cannot generated an invoice if the cart does not contains any items");

        var sub_total = calcSubTotal(cartItems);
        var total = calcTotalWithIva(sub_total, IVA);

        var invoiceHeader = createInvoiceHeader(id_user, cart.getId_cart(), sub_total, total);
        var invoiceDetailts = createInvoiceDetails(invoiceHeader.getId_invoice_hea(), cartItems);

        cartRepo.deleteFromCartItemByUserId(id_user);
        return new Invoice(
                invoiceHeader.getId_invoice_hea(),
                id_user,
                userRepo.findNameById(id_user),
                invoiceHeader.getDate_creation(),
                invoiceDetailts.stream().map(mapper::to).collect(Collectors.toSet()),
                sub_total,
                total,
                invoiceHeader.getState()
                );
    }

    private BigDecimal calcSubTotal(Set<ItemEntity> cartItems) {
        return cartItems.stream().map(x -> x.getUnit_price().multiply(new BigDecimal(x.getQuantity())))
                .reduce(BigDecimal::add).orElse(new BigDecimal(0));
    }

    private InvoiceHeaderEntity createInvoiceHeader(UUID id_user, UUID id_cart, BigDecimal sub_total,
            BigDecimal total) {
        return invoiceHeaRepo.save(new InvoiceHeaderEntity(
                null,
                id_user,
                id_cart,
                sub_total,
                total,
                new Timestamp(System.currentTimeMillis()),
                StateType.ACTIVE));
    }

    private List<InvoiceDetailEntity> createInvoiceDetails(UUID id_invoiceHeader, Collection<ItemEntity> items) {
        return invoiceDetRepo.saveAll(items.stream()
                .map(item -> new InvoiceDetailEntity(null, id_invoiceHeader, item, StateType.ACTIVE))
                .collect(Collectors.toSet()));
    }

    private BigDecimal calcTotalWithIva(BigDecimal sub_total, BigDecimal iva) {
        return sub_total.add(sub_total.multiply(iva));
    }

    @Override
    @Transactional
    public Set<Invoice> findAllInvoiceByUserId(UUID id_user) {
        var invoiceHeaders = invoiceHeaRepo.findAllByUserId(id_user);
        var invoiceDetails = invoiceDetRepo.findAllByUserId(id_user);
        var name = userRepo.findNameById(id_user);

        var resultMerge = invoiceHeaders.stream()
                .map(mapper::to)
                .peek(fact -> fact.setName(name))
                .collect(Collectors.toSet());

        resultMerge.forEach(fact -> {
            fact.setItems(mergeItems(invoiceDetails, fact));
        });

        return resultMerge;
    }

    private Set<InvoiceDetail> mergeItems(List<InvoiceDetailEntity> invoiceDetails, Invoice fact) {
        return invoiceDetails.stream()
                .filter(item -> fact.getId_invoice().equals(item.getFk_invoice()))
                .map(mapper::to)
                .collect(Collectors.toSet());
    }

    @Override
    public Invoice findInvoiceById(UUID id_invoice, UUID id_user) {
        var invoiceHeader = invoiceHeaRepo.findById(id_invoice).orElseThrow();
        if (!invoiceHeader.getFk_user().equals(id_user))
            throw new InvoiceException("You can`t look for a invoice that doesn`t belong to you");

        var invoiceDetails = invoiceDetRepo.findAllByInvoiceHeaderId(id_invoice, id_user);

        var invoice = mapper.to(invoiceHeader);
        invoice.setName(userRepo.findNameById(id_user));
        invoice.setItems(mergeItems(invoiceDetails, invoice));

        return invoice;
    }

    @Override
    @Transactional
    public void changeStateInvoiceDetailt(UUID idInvoiceDet, StateType state, UUID id_invoice, UUID id_user) {
        invoiceDetRepo.updateStateItem(state.ordinal(), idInvoiceDet, id_invoice);
        updateTotalInvoiceHeader(id_invoice, id_user);
    }

    @Override
    @Transactional
    public InvoiceDetail changeQuantityInvoiceDetailt(InvoiceDetail invoiceDet, UUID id_invoice, UUID id_user) {
        invoiceDetRepo.updateQuantityItem(invoiceDet.getQuantity(), invoiceDet.getId_invoice_det(), id_invoice);
        updateTotalInvoiceHeader(id_invoice, id_user);
        return invoiceDet;
    }

    private void updateTotalInvoiceHeader(UUID id_invoice, UUID id_user) {
        var invoiceHeader = invoiceHeaRepo.findById(id_invoice)
                .orElseThrow(() -> new InvoiceException("Invoice Incorrect"));
        if (!invoiceHeader.getFk_user().equals(id_user))
            throw new InvoiceException("You can`t look for a invoice that doesn`t belong to you");

        Set<ItemEntity> items = invoiceDetRepo.findAllByInvoiceHeaderId(id_invoice, id_user).stream()
                .filter(item -> item.getState().equals(StateType.ACTIVE))
                .map(InvoiceDetailEntity::getFk_item)
                .collect(Collectors.toSet());

        var subTotal = calcSubTotal(items);
        var total = calcTotalWithIva(subTotal, IVA);

        invoiceHeaRepo.updateMoneyById(subTotal, total, invoiceHeader);
    }

    @Override
    @Transactional
    public void changeStateInvoiceHeader(UUID id_user, UUID id_invoice, StateType state) {
        invoiceHeaRepo.updateStateById(state.ordinal(), id_user, id_invoice);
    }

}