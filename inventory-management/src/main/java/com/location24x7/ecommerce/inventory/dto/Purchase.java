package com.location24x7.ecommerce.inventory.dto;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class Purchase implements Dto {

    private Long id;

    private String billNo;

    @JsonDeserialize(using = JsonDateOnlySerializer.class)
    private Date date;

    private List<PurchaseParticular> particulars;

    private Double total;

    public Purchase() {
    }

    public Long getId() {
        return id;
    }

    public List<PurchaseParticular> getParticulars() {
        return particulars;
    }

    public String getBillNo() {
        return billNo;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setParticulars(List<PurchaseParticular> particulars) {
        this.particulars = particulars;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
