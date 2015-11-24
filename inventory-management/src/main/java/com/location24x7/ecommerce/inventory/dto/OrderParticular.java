package com.location24x7.ecommerce.inventory.dto;

public class OrderParticular implements Dto {
    
    private Long id;

    private Product product;

    private Integer quantity;

    private Double totalPrice;

    private OrderParticularStatusType status;

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderParticularStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderParticularStatusType status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    
}
