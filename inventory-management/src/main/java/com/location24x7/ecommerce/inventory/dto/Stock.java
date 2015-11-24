package com.location24x7.ecommerce.inventory.dto;

public class Stock implements Dto {

    private Long id;
    private Product product;
    private int purchased;
    private int ordered;
    private int returnedOk;
    private int returnedDefective;
    private int current;
    private int beingReturned;

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getPurchased() {
        return purchased;
    }

    public int getOrdered() {
        return ordered;
    }

    public int getReturnedOk() {
        return returnedOk;
    }

    public int getReturnedDefective() {
        return returnedDefective;
    }

    public int getCurrent() {
        return current;
    }

    public int getBeingReturned() {
        return beingReturned;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public void setReturnedOk(int returnedOk) {
        this.returnedOk = returnedOk;
    }

    public void setReturnedDefective(int returnedDefective) {
        this.returnedDefective = returnedDefective;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setBeingReturned(int beingReturned) {
        this.beingReturned = beingReturned;
    }

}
