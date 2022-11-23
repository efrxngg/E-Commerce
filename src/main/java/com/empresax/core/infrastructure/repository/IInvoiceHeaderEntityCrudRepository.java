package com.empresax.core.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresax.core.infrastructure.entity.InvoiceHeaderEntity;

public interface IInvoiceHeaderEntityCrudRepository extends JpaRepository<InvoiceHeaderEntity, UUID> {

    @Query(value = "select * from invoice_header where fk_user = :id", nativeQuery = true)
    List<InvoiceHeaderEntity> findAllByUserId(@Param("id") UUID id);

    @Modifying
    @Query(value = "update invoice_header set sub_total=:subTotal, total=:total where id_invoice_hea=:id_invoice", nativeQuery = true)
    void updateMoneyById(
            @Param("subTotal") BigDecimal subTotal,
            @Param("total") BigDecimal total,
            @Param("id_invoice") InvoiceHeaderEntity idInvoiceHeader);

    @Modifying
    @Query(value = "update invoice_header set state = :state where id_invoice_hea = :id_invoice_header  and fk_user = :id_user", nativeQuery = true)
    void updateStateById(
            @Param("state") int state,
            @Param("id_user") UUID id_user,
            @Param("id_invoice_header") UUID id_invoice_header);

}