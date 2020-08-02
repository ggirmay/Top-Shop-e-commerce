package com.shop.top.shoppingcart.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column("user_id")
    private Long userId;

    @Column("user_name")
    private String userName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    private String status;

    private float amount;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn("order_id")
    private List<OrderDetail> orderDetails;
}
