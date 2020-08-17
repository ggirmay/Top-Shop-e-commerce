package com.shop.top.shoppingcart.services;

import com.shop.top.shoppingcart.exception.RecordNotFoundException;
import com.shop.top.shoppingcart.models.ItemDetail;
import com.shop.top.shoppingcart.models.OrderDetail;
import com.shop.top.shoppingcart.models.ShoppingCart;
import com.shop.top.shoppingcart.repository.ItemDetailRepository;
import com.shop.top.shoppingcart.repository.ShoppingCartRepository;

//import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemDetailService {
    ItemDetailRepository itemDetailRepository;
    
    @Autowired
    ShoppingCartRepository shoppingCartRepo;

    @Autowired
    public ItemDetailService(ItemDetailRepository itemDetailRepository) {
        this.itemDetailRepository = itemDetailRepository;
    }


    public ItemDetail addItem(ItemDetail itemDetail){
        return itemDetailRepository.save(itemDetail);
    }

    public ItemDetail editItem(Long itemId, Long cartId, int quantity) throws RecordNotFoundException{
        ItemDetail itemDetail = itemDetailRepository.findByProductIdAndShoppingCartId(itemId, cartId);
        if (itemDetail != null){
            itemDetail.setSubTotal(quantity * itemDetail.getUnitPrice());
            itemDetail.setQuantity(quantity);
            itemDetail = itemDetailRepository.save(itemDetail);
            return itemDetail;
        }else {
            throw new RecordNotFoundException("Wrong Item ID", itemId);
        }
    }

    public List<ItemDetail> viewItems(){
        return itemDetailRepository.findAll();
    }

    public boolean deleteItem(Long itemId){
        try {
            ItemDetail temp = getItemByID(itemId);
            temp.setStatus('D');
            itemDetailRepository.save(temp);
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

    public boolean deleteItemFromShoppingCart(Long itemId, Long cartId) throws RecordNotFoundException{
        ItemDetail temp = itemDetailRepository.findByProductIdAndShoppingCartId(itemId, cartId);
        if (temp == null) {
            throw new RecordNotFoundException("This item doesn't exist in your card, item ID: ", itemId);
        }
        else {
            temp.setStatus('D');
            itemDetailRepository.save(temp);
            System.out.println("item deleted");
            return true;
        }
    }

    public List<ItemDetail> selectAllShoppingCartItems(Long cartId){
        return itemDetailRepository.selectAllShoppingCartItems(cartId);
    }
    
    public void updateItemAfterPayment(Long userId, List<OrderDetail> items){
        Optional<ShoppingCart> temp = this.shoppingCartRepo.findByUserId(userId);
//        
//        if(temp.isPresent()) {
//	        for(ItemDetail item : temp.get().getItemDetails()){
//	           for(OrderDetail orderDetail : items) {
//	        	   if(item.getProductId().equals(orderDetail.getProductId())){
//	        		   item.setStatus('P');
//	        		   itemDetailRepository.save(item);
//	        	   }
//	           }    
//	        }
//        }
    	
        if(temp.isPresent()) {
        	ItemDetail tempItemDetail;
        	for(OrderDetail item : items) {
        		 tempItemDetail = this.itemDetailRepository.findByProductIdAndShoppingCartId(item.getProductId(), temp.get().getShoppingCartId());
        		 
        		 System.out.println(tempItemDetail);
        		 
        		 if(tempItemDetail != null) {
        			 tempItemDetail.setStatus('P');
        			 this.itemDetailRepository.save(tempItemDetail);
        		 }
        	}
        	
        	
        }
    	
    	
        //else throw new RecordNotFoundException("Wrong Item ID", item.getItemId());
    }

//    public void updateItemAfterPayment(List<ItemDetail> items){
//        Optional<ItemDetail> temp;
//        for(ItemDetail item : items){
//           temp = itemDetailRepository.findById(item.getItemId());
//            if (temp.isPresent()){
//                temp.get().setStatus('P');
//                itemDetailRepository.save(temp.get());
//            }else {
//                throw new RecordNotFoundException("Wrong Item ID", item.getItemId());
//            }
//        }
//    }

    public List<ItemDetail> getAllItemsInShoppingCart(Long cartId){
        return itemDetailRepository.selectAllShoppingCartItems(cartId);
    }
}
