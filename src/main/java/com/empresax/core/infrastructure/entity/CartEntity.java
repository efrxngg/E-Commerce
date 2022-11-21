package com.empresax.core.infrastructure.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cart")
    private UUID id_cart;

    @Column(name = "fk_user")
    private UUID fk_user;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "cart_item", joinColumns = @JoinColumn(name = "fk_cart"), inverseJoinColumns = @JoinColumn(name = "fk_item"))
    private Set<ItemEntity> items = Collections.emptySet();

}