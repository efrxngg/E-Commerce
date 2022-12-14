package com.empresax.core.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresax.core.infrastructure.entity.CartEntity;

public interface ICartEntityCrudRepository extends JpaRepository<CartEntity, UUID> {

    @Query(value = "select * from cart where fk_user = :id", nativeQuery = true)
    Optional<CartEntity> findByUserId(@Param("id") UUID id);

    @Modifying
    @Query(value = """
                delete from cart_item ci where ci.fk_cart in (select ci.fk_cart from cart_item ci inner join cart c on ci.fk_cart = c.id_cart
                inner join user_x ux on c.fk_user = ux.id_user where ux.id_user = :id)
            """, nativeQuery = true)
    void deleteFromCartItemByUserId(@Param("id") UUID id);

    @Modifying
    @Query(value = "update item as i set quantity = :quant  from cart_item  ci where i.id_item = ci.fk_item  and i.id_item = :id_i", nativeQuery = true)
    void updateQuantityCartItem(
            @Param("quant") Integer quant,
            @Param("id_i") UUID id_item
            );

    @Modifying
    @Query(value = """
                delete from item i where i.id_item in (SELECT ci.fk_item  FROM cart_item ci
                INNER JOIN cart c ON ci.fk_cart=c.id_cart
                inner join user_x ux on c.fk_user = ux.id_user
                where ux.id_user = :id)
            """, nativeQuery = true)
    void deleteFromItemByUserId(@Param("id") UUID id);

}