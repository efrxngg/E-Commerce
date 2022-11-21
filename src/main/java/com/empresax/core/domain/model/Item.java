package com.empresax.core.domain.model;

import java.io.Serializable;
import java.util.UUID;

import com.empresax.core.domain.model.dto.ProductComplement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id_item;
    private ProductComplement product;
    private int quantity;

}