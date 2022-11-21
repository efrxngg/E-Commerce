package com.empresax.core.domain.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductComplement implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id_product;
    private String name;
    private BigDecimal price;
    private String img;

}