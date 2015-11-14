package com.location24x7.ecommerce.inventory.dto.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dto.Purchase;
import com.location24x7.ecommerce.inventory.dto.PurchaseParticular;
import com.location24x7.ecommerce.inventory.model.PurchaseEntity;
import com.location24x7.ecommerce.inventory.model.PurchaseParticularEntity;

@Component
public class PurchaseMapper implements Mapper<PurchaseEntity, Purchase> {

    @Autowired
    private PurchaseParticularMapper ppMapper;

    public PurchaseEntity createEntity(Purchase purchase) {
        PurchaseEntity purchaseEntity = new PurchaseEntity(purchase.getId());
        purchaseEntity.setBillNo(purchase.getBillNo());
        purchaseEntity.setDate(purchase.getDate());
        Set<PurchaseParticularEntity> particulars = ppMapper.createEntities(purchase.getParticulars(), purchaseEntity);
        purchaseEntity.setParticulars(particulars);
        Double total = 0.0;
        for (PurchaseParticularEntity purchaseParticularEntity : particulars) {
            total += purchaseParticularEntity.getTotalCost();
        }
        purchaseEntity.setTotal(total);
        return purchaseEntity;
    }

    public Purchase createDto(PurchaseEntity purchaseEntity) {
        Purchase purchase = new Purchase();
        purchase.setId(purchaseEntity.getId());
        purchase.setBillNo(purchaseEntity.getBillNo());
        purchase.setDate(purchaseEntity.getDate());
        List<PurchaseParticular> particulars = ppMapper.createDtos(purchaseEntity.getParticulars());
        purchase.setParticulars(particulars);
        Double total = 0.0;
        for (PurchaseParticular particular : particulars) {
            total += particular.getTotalCost();
        }
        purchase.setTotal(total);
        return purchase;
    }

    public List<Purchase> createDtos(Iterable<PurchaseEntity> entities) {
        List<Purchase> purchases = new ArrayList<Purchase>();
        for (PurchaseEntity entity : entities) {
            Purchase purchase = createDto(entity);
            purchases.add(purchase);
        }
        return purchases;
    }

    @Override
    public Set<PurchaseEntity> createEntities(Iterable<Purchase> dtos) {
        Set<PurchaseEntity> entities = new HashSet<PurchaseEntity>();
        for (Purchase purchase : dtos) {
            PurchaseEntity entity = createEntity(purchase);
            entities.add(entity);
        }
        return entities;
    }

}
