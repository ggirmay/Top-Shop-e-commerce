package com.financialAndReporting.financialAndReporting.controller;

import com.financialAndReporting.financialAndReporting.dto.ItemDetailList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
@RestController
@RequestMapping("/itemdetails")
@CrossOrigin
public class ItemdetailController {
    @Autowired
    RestTemplate restTemplate;


    @GetMapping(value = "/items")
    public ResponseEntity<ItemDetailList> getAllItemDetails(){
        ItemDetailList itemDetailList = restTemplate.getForObject("http://localhost:8087/itemdetail/viewItems2", ItemDetailList.class);
        return ResponseEntity.ok().body(itemDetailList);
    }
}
