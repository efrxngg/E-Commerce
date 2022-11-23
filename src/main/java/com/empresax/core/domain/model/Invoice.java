package com.empresax.core.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    // Datos de la cab factura
    private UUID id_invoice;
    private UUID id_user;
    private String name;
    private Timestamp date_invoice;

    // Datos del detalle de factura
    private Set<InvoiceDetail> items;

    // Datos de la cab factura
    private BigDecimal sub_total;
    private BigDecimal total;
    
}
