package com.location24x7.ecommerce.inventory.dto;

public class PurchaseParticular implements Dto {

    private Product product;

    private Integer quantity;

    private Double totalCost;

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
