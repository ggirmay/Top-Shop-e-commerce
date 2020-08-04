package com.shop.top.shoppingcart.repository;

import com.shop.top.shoppingcart.models.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ItemDetailRepository extends JpaRepository<ItemDetail, Long> {

    @Query(value = "select * from item_detail where shopping_cart_id = :cartId", nativeQuery = true)
    List<ItemDetail> selectAllShoppingCartItems(@Param("cartId")Long cartItem);
}
