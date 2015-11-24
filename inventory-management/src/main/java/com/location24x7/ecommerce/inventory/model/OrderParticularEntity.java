package com.location24x7.ecommerce.inventory.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity(name = "ORDER_PARTICULAR")
public class 
OrderParticularEntity implements EntityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private ProductEntity product;

    @Column
    private Integer quantity;

    @Column
    private Double totalPrice;
    
    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private OrderEntity order;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    @OrderBy("created DESC")
    private List<OrderParticularStatusEntity> statusHistory = new ArrayList<OrderParticularStatusEntity>();

    public OrderParticularEntity(Long id) {
        this.id = id;
    }

    public OrderParticularEntity() {
    }
    
    public ProductEntity getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderParticularStatusEntity> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<OrderParticularStatusEntity> statusHistory) {
        this.statusHistory = statusHistory;
    }

}
