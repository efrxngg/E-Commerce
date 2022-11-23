package com.empresax.core.domain.model;

import java.io.Serializable;
import java.util.UUID;

import com.empresax.core.domain.model.dto.ProductComplement;
import com.empresax.core.infrastructure.entity.StateType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id_invoice_det;
    private ProductComplement product;
    private int quantity;
    private StateType state;

}