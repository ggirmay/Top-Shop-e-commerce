package com.financialAndReporting.financialAndReporting.dto;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
public class ItemDetailList {
    List<ItemDetail> itemDetails;

    public ItemDetailList() {
    }

    public ItemDetailList(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public List<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }
}
