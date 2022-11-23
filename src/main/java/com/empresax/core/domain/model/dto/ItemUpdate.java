package com.empresax.core.domain.model.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdate implements Serializable {

    private UUID id_item;

    private Integer quantity;

}
