package com.shop.top.shoppingcart.controllers;

import com.shop.top.shoppingcart.exception.RecordNotFoundException;
import com.shop.top.shoppingcart.models.ItemDetail;
import com.shop.top.shoppingcart.services.ItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemdetail")
public class ItemDetailController {

    private final ItemDetailService itemDetailService;

    @Autowired
    public ItemDetailController(ItemDetailService itemDetailService) {
        this.itemDetailService = itemDetailService;
    }

    // @RequestBody annotation maps the HttpRequest body to a domain object, enabling automatic
    // deserialization of the inbound HttpRequest body onto a Java object.
    // the type we annotate with the @RequestBody annotation (in here ItemDetail) must correspond to the JSON sent
    // from our client-side controller: it means information that come to this controller must match with ItemDetail obj.
    // "ResponseEntity" represents the whole HTTP response: body, headers and status code. so we can fully configure the HTTP response
    // If we want to use it, we have to return it from the endpoint; Spring takes care of the rest.
    // we can also use it like this
    // return ResponseEntity.badRequest().body("Year of birth cannot be in the future");
    // in this way "body" should be at end. we can use many type of header like "ok, accepted, notFound, Created"
    @PostMapping("/additem")
    public ResponseEntity<ItemDetail> addItem(@RequestBody ItemDetail itemDetail){
        ItemDetail newItem = itemDetailService.addItem(itemDetail);
        return new ResponseEntity<ItemDetail>(newItem, HttpStatus.OK);
    }

    @GetMapping(value = "/viewItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDetail>> viewAllItems(){
        List<ItemDetail> allItems = itemDetailService.viewItems();
        return new ResponseEntity<List<ItemDetail>>(allItems, HttpStatus.OK);
    }

    @PutMapping("/edititem/{id}&{quantity}")
    public ResponseEntity<ItemDetail> editItem(@PathVariable("id") Long id,@PathVariable("quantity") int quantity)
            throws RecordNotFoundException {

        ItemDetail editedItem = itemDetailService.editItem(id, quantity);
        return new ResponseEntity<ItemDetail>(editedItem, HttpStatus.OK);
    }

    @DeleteMapping("/removeitem/{id}")
    public boolean deleteItem(@PathVariable("id") Long id) throws RecordNotFoundException{
        boolean result = itemDetailService.deleteItem(id);
        return result;
    }
}
