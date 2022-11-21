package com.empresax.core.infrastructure.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TagEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tag")
    private UUID id_tag;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
        name = "product_tag", 
        joinColumns = @JoinColumn(name = "fk_tag"), 
        inverseJoinColumns = @JoinColumn(name = "fk_product")
    )
    private Set<ProductEntity> products = Collections.emptySet();

    @Column(name = "state")
    private StateType state = StateType.ACTIVE;

}