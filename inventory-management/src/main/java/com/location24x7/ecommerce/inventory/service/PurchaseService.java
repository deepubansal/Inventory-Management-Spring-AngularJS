package com.location24x7.ecommerce.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.location24x7.ecommerce.inventory.dao.PurchaseDao;
import com.location24x7.ecommerce.inventory.dto.Purchase;
import com.location24x7.ecommerce.inventory.dto.mapper.PurchaseMapper;
import com.location24x7.ecommerce.inventory.model.PurchaseEntity;

@Component
public class PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private PurchaseMapper mapper;
    
    @Autowired
    private StockService stockService;

    public List<Purchase> getPurchases() {
        Iterable<PurchaseEntity> entities = purchaseDao.findAll();
        List<Purchase> purchases = mapper.createDtos(entities);
        return purchases;
    }

    public Purchase getPurchase(Long purchaseId) {
        PurchaseEntity purchaseEntity = purchaseDao.findOne(purchaseId);
        Purchase purchase = mapper.createDto(purchaseEntity);
        return purchase;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Purchase createPurchase(Purchase purchase) {
        PurchaseEntity purchaseEntity = mapper.createEntity(purchase);
        purchaseEntity = purchaseDao.save(purchaseEntity);
        purchaseEntity = purchaseDao.findOne(purchaseEntity.getId());
        stockService.purchaseMade(purchaseEntity);
        purchase = mapper.createDto(purchaseEntity);
        return purchase;
    }

}
