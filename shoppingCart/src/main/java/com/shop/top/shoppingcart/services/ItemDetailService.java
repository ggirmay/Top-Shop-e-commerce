package com.shop.top.shoppingcart.services;

import com.shop.top.shoppingcart.exception.RecordNotFoundException;
import com.shop.top.shoppingcart.models.ItemDetail;
import com.shop.top.shoppingcart.repository.ItemDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemDetailService {
    ItemDetailRepository itemDetailRepository;

    @Autowired
    public ItemDetailService(ItemDetailRepository itemDetailRepository) {
        this.itemDetailRepository = itemDetailRepository;
    }


    public ItemDetail addItem(ItemDetail itemDetail){
        return itemDetailRepository.save(itemDetail);
    }

    public ItemDetail editItem(Long itemId, int quantity) throws RecordNotFoundException{
        Optional<ItemDetail> itemDetail = itemDetailRepository.findById(itemId);
        if (itemDetail.isPresent()){
            ItemDetail temp = itemDetail.get();
            temp.setSubTotal(quantity * temp.getUnitPrice());
            temp.setQuantity(quantity);
            temp = itemDetailRepository.save(temp);
            return temp;
        }else {
            throw new RecordNotFoundException("Wrong Item ID", itemId);
        }

    }

    public List<ItemDetail> viewItems(){
        return itemDetailRepository.findAll();
    }

    public boolean deleteItem(Long itemId){
        try {
            getItemByID(itemId);
            itemDetailRepository.deleteById(itemId);
            return true;
        }catch (RecordNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ItemDetail getItemByID(Long itemId) throws RecordNotFoundException{
        Optional<ItemDetail> itemDetail = itemDetailRepository.findById(itemId);
        if (itemDetail.isPresent()){
            return itemDetail.get();
        }else {
            throw new RecordNotFoundException("This item doesn't exist in your card, item ID: ", itemId);
        }
    }

    public List<ItemDetail> selectAllShoppingCartItems(Long cartId){
        return itemDetailRepository.selectAllShoppingCartItems(cartId);
    }
}
