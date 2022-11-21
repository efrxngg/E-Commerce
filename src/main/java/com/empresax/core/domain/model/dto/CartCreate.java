package com.empresax.core.domain.model.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartCreate implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id_cart;

    private UUID id_user;

    @NotNull(message = "The items cannot be null")
    private Set<ItemCreate> items;
    
}