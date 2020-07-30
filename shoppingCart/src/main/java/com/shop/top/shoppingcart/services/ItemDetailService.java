package com.shop.top.shoppingcart.services;

import com.shop.top.shoppingcart.exception.RecordNotFoundException;
import com.shop.top.shoppingcart.models.ItemDetail;
import com.shop.top.shoppingcart.repository.ItemDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemDetailService {
    ItemDetailRepository itemDetailRepository;

    public ItemDetailService(ItemDetailRepository itemDetailRepository) {
        this.itemDetailRepository = itemDetailRepository;
    }


    public ItemDetail addItem(ItemDetail itemDetail){
        return itemDetailRepository.save(itemDetail);
    }

    public List<ItemDetail> viewItems(){
        return itemDetailRepository.findAll();
    }

    public void deleteItem(long itemId) throws RecordNotFoundException{
        Optional<ItemDetail> item = itemDetailRepository.findById(itemId);
        if (item.isPresent()){
            itemDetailRepository.deleteById(itemId);
        }else {
            throw new RecordNotFoundException("Item doesn't exist in your card for deletion", itemId);
        }
    }
}
