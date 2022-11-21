package com.empresax.core.infrastructure.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice_detail")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice_det")
    private UUID id_invoice_det;

    @Column(name = "fk_invoice")
    private UUID fk_invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_item", referencedColumnName = "id_item", insertable = false, updatable = false, nullable = false)
    private ItemEntity fk_item;

    @Enumerated(EnumType.ORDINAL)
    private StateType state;

}