package com.location24x7.ecommerce.inventory.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity(name = "ORDER_DETAILS")
public class OrderEntity implements EntityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String invoiceNo;

    @Column
    private Date date;

    @Column
    private String orderId;

    @Column
    private String status; 
    
    @Column
    private Double fees;
    
    @Column
    private Double shippingCharges;

    @Column
    private Double totalPrice;
    
    @Column
    private String customerName;
    
    @Column
    private String customerAddress;
    
    @Column
    private String sellerPortal;
    
    @Column
    private Date refundDate;

    @Column
    private Date created;

    @Column
    private Date updated;
    

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderParticularEntity> particulars;

    public OrderEntity(Long id) {
        super();
        this.id = id;
    }

    public OrderEntity() {
    }

    public Long getId() {
        return id;
    }

    public Set<OrderParticularEntity> getParticulars() {
        return particulars;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParticulars(Set<OrderParticularEntity> particulars) {
        this.particulars = particulars;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public Double getFees() {
        return fees;
    }

    public Double getShippingCharges() {
        return shippingCharges;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getSellerPortal() {
        return sellerPortal;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public void setShippingCharges(Double shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setSellerPortal(String sellerPortal) {
        this.sellerPortal = sellerPortal;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
