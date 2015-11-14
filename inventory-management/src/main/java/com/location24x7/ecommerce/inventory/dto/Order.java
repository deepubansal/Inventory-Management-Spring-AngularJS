package com.location24x7.ecommerce.inventory.dto;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;


/*
 * Sample Request:
 * {
    "invoiceNo": "1001",
    "orderId": "302-123-343-34343",
    "date": "14-11-2015 20:11:00",
    "sellerPortal": "Amazon",
    "fees": 156.44,
    "shippingCharges": 218.3,
    "particulars": [
        {
            "product": {
                "id": 5
            },
            "quantity": 2,
            "totalPrice": 199
        },
        {
            "product": {
                "id": 3
            },
            "quantity": 1,
            "totalPrice": 1100
        }
    ]
}
*/

public class Order implements Dto {

    private Long id;

    private String orderId;
    
    private SellerPortalType sellerPortal;
    
    private OrderStatusType status;

    @JsonDeserialize(using = JsonDateTimeSerializer.class)
    private Date date;
    
    private Double fees;
    
    private Double shippingCharges;
    
    private Double totalPrice;

    private List<OrderParticular> particulars;

    private String invoiceNo;

    private String customerName;

    private String customerAddress;
    
    @JsonDeserialize(using = JsonDateOnlySerializer.class)
    private Date refundDate;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public List<OrderParticular> getParticulars() {
        return particulars;
    }

    public String getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParticulars(List<OrderParticular> particulars) {
        this.particulars = particulars;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public OrderStatusType getStatus() {
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

    public SellerPortalType getSellerPortal() {
        return sellerPortal;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public void setStatus(OrderStatusType status) {
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

    public void setSellerPortal(SellerPortalType sellerPortal) {
        this.sellerPortal = sellerPortal;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

}
