package com.empresax.core.infrastructure.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice_header")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceHeaderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice_hea")
    private UUID id_invoice_hea;

    @Column(name = "fk_user")
    private UUID fk_user;

    @Column(name = "fk_cart")
    private UUID fk_cart;

    @Column(name = "sub_total")
    private BigDecimal sub_total;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "date_creation")
    private Timestamp date_creation;

    @Enumerated(EnumType.ORDINAL)
    private StateType state;

}