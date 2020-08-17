package com.shop.top.shoppingcart.controllers;

import com.shop.top.shoppingcart.dto.CartItem;
import com.shop.top.shoppingcart.dto.ProductDTO;
import com.shop.top.shoppingcart.exception.RecordNotFoundException;
import com.shop.top.shoppingcart.models.ItemDetail;
import com.shop.top.shoppingcart.models.ShoppingCart;
import com.shop.top.shoppingcart.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/shoppingcart")
@CrossOrigin(origins = "*")
public class ShoppingCartController {

    ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/createnewcart")
    public ShoppingCart createShoppingCart(@RequestBody ShoppingCart shoppingCart){
    	System.out.println("USER ID.........." + shoppingCart.getUserId());
        return shoppingCartService.creatShoppingCart(shoppingCart);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/cartid/{userid}")
    public ResponseEntity<?> getShoppingCartId(@PathVariable("userid") Long userId) {
    	
		try {
			ShoppingCart shoppingCart = shoppingCartService.getShoppingCartId(userId);
			return ResponseEntity.ok(shoppingCart.getShoppingCartId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Cannot found Shopping Cart");
		}
        
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/cart-id")
    public List<CartItem> getShoppingCartIdDTO(@RequestParam("userId") Long userId) {
        ShoppingCart shoppingCart;
		try {
			shoppingCart = shoppingCartService.getShoppingCartId(userId);
			
			List<ItemDetail> list = shoppingCart.getItemDetails();
	        
	        List<CartItem> items = new ArrayList<>();
	        if(list != null) {
	            for(ItemDetail item : shoppingCart.getItemDetails()) {
	            	if(item.getStatus() == 'A')
	            	items.add(new CartItem(new ProductDTO(item.getProductId(), item.getProductName(), item.getUnitPrice(), item.getSubTotal()), item.getQuantity()));
	            }
	        }
	        
	        return items;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
        
    }

    @PostMapping("/additem/{cartid}")
    public ShoppingCart addItemToCart(@PathVariable("cartid") Long id, @RequestBody ItemDetail itemDetail)
    throws RecordNotFoundException{
        System.out.println("this is shopping cart controller " + id);
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
    
    @PostMapping("/save-to-cart")
    public boolean saveCart(@RequestBody CartItem[] items , @RequestParam("cartId") Long cartId) {
    	ShoppingCart shoppingCart;
		try {
			shoppingCart = this.shoppingCartService.getById(cartId);
			return this.shoppingCartService.saveItemstoCart(shoppingCart, items);
		} catch (Exception e) {
			e.printStackTrace();
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
