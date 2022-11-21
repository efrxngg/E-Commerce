package com.empresax.core.domain.service;

import java.util.UUID;

import com.empresax.core.domain.model.Cart;
import com.empresax.core.domain.model.dto.CartCreate;
import com.empresax.core.domain.model.dto.ItemUpdate;

public interface ICartService {

    CartCreate saveOrUpdateCart(CartCreate item);

    Cart getCartByUserId(UUID id);

    ItemUpdate updateQuantityByItemId(ItemUpdate item);

    void cleanCartByUserId(UUID id);

}