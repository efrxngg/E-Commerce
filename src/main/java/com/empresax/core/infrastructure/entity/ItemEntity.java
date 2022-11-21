package com.empresax.core.infrastructure.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Lo que el usuario pone en su carrito
 */

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id_item")
    private UUID id_item;

    @ManyToOne
    @JoinColumn(name = "fk_product")
    private ProductEntity fk_product;

    @Column(name = "unit_price")
    private BigDecimal unit_price;

    @Column(name = "quantity")
    private Integer quantity;

}