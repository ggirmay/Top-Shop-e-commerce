package com.shop.top.shoppingcart.controllers;

import com.shop.top.shoppingcart.models.ItemDetail;
import com.shop.top.shoppingcart.services.ItemDetailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemdetail")
public class ItemDetailController {

    private final ItemDetailService itemDetailService;

    public ItemDetailController(ItemDetailService itemDetailService) {
        this.itemDetailService = itemDetailService;
    }

    @PostMapping("/additem")
    public ItemDetail addItem(@RequestBody ItemDetail itemDetail){
        return itemDetailService.addItem(itemDetail);
    }
}
