package com.empresax.core.domain.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresax.core.domain.exception.CartNotFoundException;
import com.empresax.core.domain.exception.ItemException;
import com.empresax.core.domain.model.Cart;
import com.empresax.core.domain.model.dto.CartCreate;
import com.empresax.core.domain.model.dto.ItemCreate;
import com.empresax.core.domain.model.dto.ItemUpdate;
import com.empresax.core.domain.service.ICartService;
import com.empresax.core.infrastructure.config.Mapper;
import com.empresax.core.infrastructure.entity.CartEntity;
import com.empresax.core.infrastructure.entity.ItemEntity;
import com.empresax.core.infrastructure.repository.ICartEntityCrudRepository;
import com.empresax.core.infrastructure.repository.IItemEntityCrudRepository;
import com.empresax.core.infrastructure.repository.IProductEntityCrudRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements ICartService {

    private ICartEntityCrudRepository cartEntityCrudRepository;
    private IProductEntityCrudRepository productEntityCrudRepository;
    private IItemEntityCrudRepository itemEntityCrudRepository;
    private Mapper mapper;

    @Override
    @Transactional
    public CartCreate saveOrUpdateCart(CartCreate cart) {
        var cartDB = cartEntityCrudRepository.findByUserId(cart.getId_user());
        if (cartDB.isPresent())
            return updateCart(cart, cartDB);

        cartEntityCrudRepository.save(new CartEntity(null, cart.getId_user(), getAllCartItems(cart)));
        return cart;
    }

    private CartCreate updateCart(CartCreate cart, Optional<CartEntity> cartDB) {
        var itemsCart = getAllCartItems(cart);
        var itemsDB = cartDB.get().getItems();
        var itemsCombinedNotRepeat = startItemFilters(itemsCart, itemsDB);

        cart.setId_cart(cartDB.get().getId_cart());
        cartEntityCrudRepository.save(new CartEntity(
                cart.getId_cart(),
                cart.getId_user(),
                itemsDB.isEmpty() ? itemsCart : itemsCombinedNotRepeat));
        return cart;
    }

    private Set<ItemEntity> startItemFilters(Set<ItemEntity> itemsCart, Set<ItemEntity> itemsDB) {
        var itemsUpdate = filterItemsUpdate(itemsCart, itemsDB);
        var itemsSave = filterItemsSave(itemsCart, itemsUpdate);
        var combined = mergeSets(itemsUpdate, itemsSave);
        var itemsDelete = filterItemsDelete(itemsDB, combined);
        var itemsCombinedNotRepeat = filterDistintByProductName(combined);
        itemsSave.stream()
                .filter(a -> !itemsCombinedNotRepeat.stream().anyMatch(b -> equalsProductName(a, b)))
                .forEach(itemsCombinedNotRepeat::add);
        itemEntityCrudRepository.deleteAll(itemsDelete);
        return itemsCombinedNotRepeat;
    }

    private List<ItemEntity> filterItemsDelete(Set<ItemEntity> itemsDB, Set<ItemEntity> combined) {
        return itemsDB.stream().filter(a -> !combined.contains(a)).collect(Collectors.toList());
    }

    private Set<ItemEntity> filterDistintByProductName(Set<ItemEntity> combined) {
        Set<String> listProductNames = new HashSet<>();
        var itemsCombinedNotRepeat = combined.stream()
                .peek(x -> listProductNames.add(x.getFk_product().getName()))
                .filter(x -> listProductNames.contains(x.getFk_product().getName()))
                .filter(x -> Objects.nonNull(x.getId_item()))
                .collect(Collectors.toSet());
        listProductNames.clear();
        return itemsCombinedNotRepeat;
    }

    private Set<ItemEntity> mergeSets(Set<ItemEntity> update, Set<ItemEntity> save) {
        var combined = Stream.concat(update.stream(), save.stream())
                .collect(Collectors.toSet());
        return combined;
    }

    private Set<ItemEntity> filterItemsSave(Set<ItemEntity> items, Set<ItemEntity> update) {
        var save = items.stream()
                .filter(a -> update.stream().anyMatch(b -> !equalsItemId(a, b)))
                .collect(Collectors.toSet());
        return save;
    }

    private Set<ItemEntity> filterItemsUpdate(Set<ItemEntity> items, Set<ItemEntity> itemsDB) {
        var update = itemsDB.stream()
                .filter(a -> items.stream().anyMatch(b -> equalsItemId(a, b)))
                .collect(Collectors.toSet());
        return update;
    }

    private boolean equalsProductName(ItemEntity a, ItemEntity b) {
        return a.getFk_product().getName().equals(b.getFk_product().getName());
    }

    private boolean equalsItemId(ItemEntity a, ItemEntity b) {
        return a.getFk_product().getId_product().equals(b.getFk_product().getId_product());
    }

    private Set<ItemEntity> getAllCartItems(CartCreate cart) {
        return productEntityCrudRepository.findAllById(cart.getItems().stream()
                .map(ItemCreate::getId_product)
                .collect(Collectors.toSet()))
                .stream()
                .map(p -> new ItemEntity(null, p, p.getPrice(),
                        getQuantity(cart.getItems(), p.getId_product())))
                .collect(Collectors.toSet());
    }

    private int getQuantity(Set<ItemCreate> items, UUID id) {
        return items.stream()
                .filter(x -> x.getId_product().equals(id))
                .findFirst().get().getQuantity();
    }

    @Override
    public Cart getCartByUserId(UUID id) {
        return mapper.to(cartEntityCrudRepository.findByUserId(id)
                .orElseThrow(() -> new CartNotFoundException("Cart Not Found")));
    }

    @Override
    @Transactional
    public ItemUpdate updateQuantityByItemId(ItemUpdate item) {
        try {
            cartEntityCrudRepository.updateQuantityCartItem(item.getQuantity(), item.getId_item());
        } catch (Exception e) {
            throw new ItemException("The item or cart id is incorrect");
        }

        return item;
    }

    /**
     * Este metodo solo debe ser usado si el item paso del carrito al detalle de
     * factura
     */
    @Override
    @Transactional
    public void cleanCartByUserId(UUID id) {
        try {
            cartEntityCrudRepository.deleteFromItemByUserId(id);
        } catch (Exception e) {
            throw new ItemException("The item or cart id is incorrect");
        }
    }

}