package com.location24x7.ecommerce.inventory.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.location24x7.ecommerce.inventory.model.PurchaseEntity;

@Repository
public interface PurchaseDao extends CrudRepository<PurchaseEntity, Long> {

    public List<PurchaseEntity> findByBillNo(String billNo);

    public List<PurchaseEntity> findByDate(Date date);
}
