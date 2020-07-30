package com.shop.top.shoppingcart.repository;

import com.shop.top.shoppingcart.models.ItemDetail;
import org.springframework.data.repository.CrudRepository;


public interface ProductDetailRepository extends CrudRepository<ItemDetail, Long> {

}
