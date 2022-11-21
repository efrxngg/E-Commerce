package com.empresax.core.domain.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private UUID id_cart;

    private UUID id_user;

    private Set<Item> items;

}