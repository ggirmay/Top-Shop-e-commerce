package com.shop.top.shoppingcart.controllers;

import com.shop.top.shoppingcart.exception.RecordNotFoundException;
import com.shop.top.shoppingcart.models.ItemDetail;
import com.shop.top.shoppingcart.models.ShoppingCart;
import com.shop.top.shoppingcart.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/createnewcart")
    public ShoppingCart createShoppingCart(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.creatShoppingCart(shoppingCart);
    }

    @PutMapping(value = "additme/{cartid}")
    public ShoppingCart addItemToCart(@PathVariable("cartid") Long id, @RequestBody ItemDetail itemDetail)
    throws RecordNotFoundException{
        return shoppingCartService.addItemToCart(id,itemDetail);
    }

    @DeleteMapping("/deleteitem/{cartid}&{itemid}")
    public boolean deleteItemFromShoppingCart(@PathVariable("cartid") Long id, @PathVariable("itemid") Long item)
    throws RecordNotFoundException {
        try {
            return shoppingCartService.deleteItemFromCart(id, item);
        }catch (RecordNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @GetMapping("/allitems/{cartid}")
    public List<ItemDetail> viewAllItemOfCart(@PathVariable("cartid") Long cartId){
        return shoppingCartService.viewAllItemOfCart(cartId);
    }

    @PutMapping("/editquantity/{itemid}&{quantity}")
    public ItemDetail editQuantityOfItemInCart(@PathVariable("itemid")Long itemId, @PathVariable("quantity")int quantity){
        return shoppingCartService.editQuantityOfItemInCart(itemId, quantity);
    }
}
