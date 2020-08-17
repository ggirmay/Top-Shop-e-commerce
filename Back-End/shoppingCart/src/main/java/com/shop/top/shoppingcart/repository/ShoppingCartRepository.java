package com.shop.top.shoppingcart.repository;

import com.shop.top.shoppingcart.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findAllByItemDetailsEmpty();
    
    @Query("select s from ShoppingCart s where s.userId = :userId")
    Optional<ShoppingCart> findByUserId(@Param("userId") Long userId);
}