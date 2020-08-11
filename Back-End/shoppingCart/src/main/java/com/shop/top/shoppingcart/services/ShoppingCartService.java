package com.shop.top.shoppingcart.services;

import com.shop.top.shoppingcart.exception.RecordNotFoundException;
import com.shop.top.shoppingcart.models.ItemDetail;
import com.shop.top.shoppingcart.models.ShoppingCart;
import com.shop.top.shoppingcart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;
    ItemDetailService itemDetailService;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ItemDetailService itemDetailService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemDetailService = itemDetailService;
    }

    public ShoppingCart creatShoppingCart(ShoppingCart shoppingCart){
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart addItemToCart(Long cartId, ItemDetail itemDetail) throws RecordNotFoundException {
        Optional<ShoppingCart> shoppingCart =  shoppingCartRepository.findById(cartId);
        ShoppingCart result = null;

        if(shoppingCart.isPresent()){
            result = shoppingCart.get();
            result.getItemDetails().add(itemDetail);
            shoppingCartRepository.save(result);
        }else{
            throw new RecordNotFoundException("Shopping Cart doesn't exist, Shopping Cart ID: ", cartId);
        }

        return result;
    }

    public boolean deleteItemFromCart(Long itemId, Long cartId) throws RecordNotFoundException {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(cartId);

        if(shoppingCart.isPresent()) {
            return itemDetailService.deleteItemFromShoppingCart(itemId, cartId);
        }
        else
            throw new RecordNotFoundException("Shopping cart ID doesn't exist: ", cartId);
    }

    public List<ItemDetail> viewAllItemOfCart(Long cartId) {
        return itemDetailService.selectAllShoppingCartItems(cartId);
    }

    public ItemDetail editQuantityOfItemInCart(Long itemId, Long cartId, int quantity){
        try {
            return itemDetailService.editItem(itemId, cartId, quantity);
        }catch (RecordNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
