package com.empresax.core.infrastructure.config;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.empresax.core.domain.model.Cart;
import com.empresax.core.domain.model.Item;
import com.empresax.core.domain.model.Product;
import com.empresax.core.domain.model.User;
import com.empresax.core.domain.model.dto.ProductComplement;
import com.empresax.core.infrastructure.entity.CartEntity;
import com.empresax.core.infrastructure.entity.ItemEntity;
import com.empresax.core.infrastructure.entity.ProductEntity;
import com.empresax.core.infrastructure.entity.UserEntity;

@Component
public class Mapper {

    public static UserEntity to(User user, UUID id) {
        return new UserEntity(
                id,
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFist_name(),
                user.getLast_name(),
                user.getPhone_number(),
                null,
                null,
                null,
                null);
    }

    public static User to(UserEntity user) {
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFist_name(),
                user.getLast_name(),
                user.getPhone_number());
    }

    public Product to(ProductEntity ent) {
        return new Product(
                ent.getId_product(),
                ent.getName(),
                ent.getPrice(),
                ent.getCount(),
                ent.getImg_url());
    }

    public ProductComplement to2(ProductEntity product) {
        return new ProductComplement(
                product.getId_product(),
                product.getName(),
                product.getPrice(),
                product.getImg_url());
    }

    public Item to(ItemEntity item) {
        return new Item(item.getId_item(), to2(item.getFk_product()), item.getQuantity());
    }

    public Cart to(CartEntity cart) {
        return new Cart(
                cart.getId_cart(),
                cart.getFk_user(),
                cart.getItems().stream().map(x -> to(x)).collect(Collectors.toSet()));
    }

}