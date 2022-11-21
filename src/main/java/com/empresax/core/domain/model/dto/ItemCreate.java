package com.empresax.core.domain.model.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "The id_product cannot be null")
    @NotBlank(message = "The id_product cannot be empty")
    private UUID id_product;

    @NotNull(message = "The quantity cannot be null")
    private int quantity;

}