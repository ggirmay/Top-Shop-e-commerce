package com.shop.top.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Orders {

    public Orders() {
    }

    public Orders(Long id, Long userId, String userName, Date createdDate, String status, float amount){
        this.orderId = id;
        this.userId = userId;
        this.userName = userName;
        this.createdDate = createdDate;
        this.status = status;
        this.amount = amount;
    }
    
    public Orders(Long userId, String userName, Date createdDate, String status, float amount){
        this.userId = userId;
        this.userName = userName;
        this.createdDate = createdDate;
        this.status = status;
        this.amount = amount;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "created_date", columnDefinition = "DATE")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date createdDate;

    private String status;

    private float amount;

    @Transient
    String error;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> orderDetails;

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", userId=" + userId + ", userName=" + userName + ", createdDate="
				+ createdDate + ", status=" + status + ", amount=" + amount + ", error=" + error + ", orderDetails="
				+ orderDetails + "]";
	}
    
    
}
