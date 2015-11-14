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

@Entity(name = "purchase")
public class PurchaseEntity implements EntityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String billNo;

    @Column
    private Date date;

    @Column
    private Double total;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase", fetch = FetchType.EAGER)
    private Set<PurchaseParticularEntity> particulars;

    public PurchaseEntity(Long id) {
        super();
        this.id = id;
    }

    public PurchaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public Set<PurchaseParticularEntity> getParticulars() {
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

    public void setParticulars(Set<PurchaseParticularEntity> particulars) {
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
