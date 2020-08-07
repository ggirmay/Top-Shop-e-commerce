package com.shop.top.productservice.productservice.service;

import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.model.Promotion;
import com.shop.top.productservice.productservice.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {
   private final  PromotionRepository promotionRepository;
   @Autowired
   public PromotionServiceImpl( PromotionRepository promotionRepository){
       this.promotionRepository=promotionRepository;
   }
    @Override
    public List<Product> getAllPromotedProducts() {

        List<Promotion> promotions= promotionRepository.findActivePromontion(Calendar.getInstance().getTime());
       List<Product>products=new ArrayList<>();


           for(Promotion promotion:promotions){
          products.addAll(promotion.getProduct());
        }


return  products;
    }
}
