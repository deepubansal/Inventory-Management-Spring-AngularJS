package com.location24x7.ecommerce.inventory.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity(name = "ORDER_PARTICULAR_STATUS")
public class OrderParticularStatusEntity implements EntityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @Column
    private Date created;

    @Column
    private String reason;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "ORDER_PARTICULAR_ID", nullable = false)
    private OrderParticularEntity order;

    public OrderParticularEntity getOrder() {
        return order;
    }

    public void setOrder(OrderParticularEntity order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
