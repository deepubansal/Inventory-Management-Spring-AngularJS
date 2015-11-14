package com.location24x7.ecommerce.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "purchase_particular")
public class PurchaseParticularEntity implements EntityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ProductEntity product;

    @Column
    private Integer quantity;

    @Column
    private Double totalCost;

    @ManyToOne
    @JoinColumn(name = "PURCHASE_ID", nullable = false)
    private PurchaseEntity purchase;

    public ProductEntity getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public PurchaseEntity getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseEntity purchase) {
        this.purchase = purchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
