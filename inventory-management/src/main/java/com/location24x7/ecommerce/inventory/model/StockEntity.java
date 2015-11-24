package com.location24x7.ecommerce.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "STOCK")
public class StockEntity implements EntityType {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private int purchased;
        
    @Column
    private int returnedOK;
    
    @Column
    private int returnedDefective;
    
    @Column
    private int beingReturned;

    @Column
    private int ordered;
    
    @Column
    private int current;
    
    public StockEntity() {
    }

    public StockEntity(Long id) {
        this.id = id;
    }
    
    public ProductEntity getProduct() {
        return product;
    }

    public Long getId() {
        return id;
    }

    public int getPurchased() {
        return purchased;
    }

    public int getReturnedOK() {
        return returnedOK;
    }

    public int getReturnedDefective() {
        return returnedDefective;
    }

    public int getBeingReturned() {
        return beingReturned;
    }

    public int getOrdered() {
        return ordered;
    }

    public int getCurrent() {
        return current;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public void setReturnedOK(int returnedOK) {
        this.returnedOK = returnedOK;
    }

    public void setReturnedDefective(int returnedDefective) {
        this.returnedDefective = returnedDefective;
    }

    public void setBeingReturned(int beingReturned) {
        this.beingReturned = beingReturned;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public void setCurrent(int current) {
        this.current = current;
    }


}
