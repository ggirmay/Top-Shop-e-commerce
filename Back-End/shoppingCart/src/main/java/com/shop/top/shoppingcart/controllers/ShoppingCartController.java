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
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @PostMapping("/additem/{cartid}")
    public ShoppingCart addItemToCart(@PathVariable("cartid") Long id, @RequestBody ItemDetail itemDetail)
    throws RecordNotFoundException{
        return shoppingCartService.addItemToCart(id,itemDetail);
    }

    @PutMapping("/deleteitem")
    public boolean deleteItemFromShoppingCart(@RequestParam("productid") Long itemId, @RequestParam("cartid") Long cartId)
    throws RecordNotFoundException {
        System.out.println("this is controller: " + itemId + "  :  " + cartId);
        try {
            return shoppingCartService.deleteItemFromCart(itemId, cartId);
        }catch (RecordNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @GetMapping("/allitems/{cartid}")
    public List<ItemDetail> viewAllItemOfCart(@PathVariable("cartid") Long cartId){
        return shoppingCartService.viewAllItemOfCart(cartId);
    }

    @PutMapping("/editquantity")
    public ItemDetail editQuantityOfItemInCart(@RequestParam("itemid")Long itemId, @RequestParam("cartid") Long cartId,
                                               @RequestParam("quantity")int quantity){
        return shoppingCartService.editQuantityOfItemInCart(itemId, cartId, quantity);
    }

    @GetMapping("/allshoppingcarts")
    public List<ShoppingCart> allShoppingCarts(){
        return shoppingCartService.getAllShoppingCarts();
    }
}
