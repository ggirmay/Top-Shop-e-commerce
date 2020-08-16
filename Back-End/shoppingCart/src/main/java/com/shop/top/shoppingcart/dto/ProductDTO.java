package com.shop.top.shoppingcart.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    Long productId;
    String productName;
    double unitPrice;
    double subtotalPrice;
	
    public ProductDTO() {
		super();
	}

	public ProductDTO(Long productId, String productName, double unitPrice, double subtotalPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.subtotalPrice = subtotalPrice;
	}
    
}
