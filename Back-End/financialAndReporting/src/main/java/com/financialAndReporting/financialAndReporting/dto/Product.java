package com.financialAndReporting.financialAndReporting.dto;

import javax.persistence.*;

//@Entity
public class Product {
    private Integer id;
    private String name;
    private String code;
    private String type;
    private String price;
    private String pictureUrl;
    private Category category;

    public Product() {
    }

    public Product(Integer id, String name, String code, String type, String price, String pictureUrl, Category category) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.type = type;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
