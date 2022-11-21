package com.empresax.core.domain.repository;

import com.empresax.core.domain.model.Item;

public interface IItemRepository {

    Item updateQuantityById(Item entity);
    
}