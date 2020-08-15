package com.shop.top.shoppingcart.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.top.shoppingcart.models.OrderDetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

    private String userId;
    private String userName;
    
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date createdDate;
    private String status;
    private float amount;
    private List<OrderDetail> orderDetails;
}
