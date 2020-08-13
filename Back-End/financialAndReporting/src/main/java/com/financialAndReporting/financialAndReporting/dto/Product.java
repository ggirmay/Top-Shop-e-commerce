package com.financialAndReporting.financialAndReporting.dto;


public class Product{
    private Long id;
    private String name;
    private String status;
    private String code;
    private String type;
    private Double price;
    private  int quantity;
    private String picture_url;
    private Category category;
    private ProductDetail productDetail;
    private double salesPrice;
    private double discount;
    private String shortDetails;
    private String description;
    private boolean newPro;
    private boolean sale;
    private String brand;

    public Product() {
    }

    public Product(Long id, String name, String status, String code, String type, Double price, int quantity, String picture_url, Category category, ProductDetail productDetail, double salesPrice, double discount, String shortDetails, String description, boolean newPro, boolean sale, String brand) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.code = code;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.picture_url = picture_url;
        this.category = category;
        this.productDetail = productDetail;
        this.salesPrice = salesPrice;
        this.discount = discount;
        this.shortDetails = shortDetails;
        this.description = description;
        this.newPro = newPro;
        this.sale = sale;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getShortDetails() {
        return shortDetails;
    }

    public void setShortDetails(String shortDetails) {
        this.shortDetails = shortDetails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNewPro() {
        return newPro;
    }

    public void setNewPro(boolean newPro) {
        this.newPro = newPro;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
