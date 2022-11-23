package com.empresax.core.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresax.core.infrastructure.entity.InvoiceDetailEntity;

public interface InvoiceDetailEntityCrudRepository extends JpaRepository<InvoiceDetailEntity, UUID> {

    @Query(value = "select * from invoice_detail id inner join invoice_header ih on id.fk_invoice = ih.id_invoice_hea where ih.fk_user = :id_user ", nativeQuery = true)
    List<InvoiceDetailEntity> findAllByUserId(@Param("id_user") UUID id);

    @Query(value = """
            select * from invoice_detail id
            inner join invoice_header ih on id.fk_invoice = ih.id_invoice_hea
            where ih.id_invoice_hea = :id_inv_hea and ih.fk_user = :id_user
                """, nativeQuery = true)
    List<InvoiceDetailEntity> findAllByInvoiceHeaderId(
            @Param("id_inv_hea") UUID id_invoice,
            @Param("id_user") UUID id_user);

    @Modifying
    @Query(value = "update invoice_detail set state = :sts where id_invoice_det = :id_inv_det and fk_invoice = :id_invoice", nativeQuery = true)
    void updateStateItem(
            @Param("sts") int state,
            @Param("id_inv_det") UUID id_invoice_det,
            @Param("id_invoice") UUID id_invoice);

    @Modifying
    @Query(value = "update item i set quantity = :quant from invoice_detail id where i.id_item = id.fk_item and id.id_invoice_det = :id_inv_det and id.fk_invoice = :id_invoice", nativeQuery = true)
    void updateQuantityItem(
            @Param("quant") int quantity,
            @Param("id_inv_det") UUID id_invoice_det,
            @Param("id_invoice") UUID id_invoice);

}