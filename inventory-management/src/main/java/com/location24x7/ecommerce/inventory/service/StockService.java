package com.location24x7.ecommerce.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dao.StockDao;
import com.location24x7.ecommerce.inventory.dto.OrderParticularStatusType;
import com.location24x7.ecommerce.inventory.dto.Stock;
import com.location24x7.ecommerce.inventory.dto.mapper.StockMapper;
import com.location24x7.ecommerce.inventory.model.OrderEntity;
import com.location24x7.ecommerce.inventory.model.OrderParticularEntity;
import com.location24x7.ecommerce.inventory.model.OrderParticularStatusEntity;
import com.location24x7.ecommerce.inventory.model.ProductEntity;
import com.location24x7.ecommerce.inventory.model.PurchaseEntity;
import com.location24x7.ecommerce.inventory.model.PurchaseParticularEntity;
import com.location24x7.ecommerce.inventory.model.StockEntity;

@Component
public class StockService {
    
    @Autowired
    private StockDao stockDao;
    
    @Autowired
    private StockMapper stockMapper;
    
    
    protected Stock initializeStock(ProductEntity productEntity) {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setProduct(productEntity);
        stockEntity = stockDao.save(stockEntity);
        Stock stock = stockMapper.createDto(stockEntity);
        return stock;
    }


    protected void purchaseMade(PurchaseEntity purchaseEntity) {
        Set<PurchaseParticularEntity> purchases = purchaseEntity.getParticulars();
        for (PurchaseParticularEntity purchase : purchases) {
            StockEntity stockEntity = stockDao.findByProduct(purchase.getProduct()).get(0);
            int current = stockEntity.getCurrent();
            int purchased = stockEntity.getPurchased();
            stockEntity.setCurrent(current + purchase.getQuantity());
            stockEntity.setPurchased(purchased + purchase.getQuantity());
            stockDao.save(stockEntity);
        }
    }
    
    protected void orderRecieved(OrderEntity orderEntity) {
        Set<OrderParticularEntity> orders = orderEntity.getParticulars();
        for (OrderParticularEntity order : orders) {
            StockEntity stockEntity = stockDao.findByProduct(order.getProduct()).get(0);
            int current = stockEntity.getCurrent();
            stockEntity.setCurrent(current - order.getQuantity());
            stockDao.save(stockEntity);
        }
    }

    protected void orderReturned(OrderEntity orderEntity) {
        Set<OrderParticularEntity> orders = orderEntity.getParticulars();
        for (OrderParticularEntity order : orders) {
            StockEntity stockEntity = stockDao.findByProduct(order.getProduct()).get(0);
            int current = stockEntity.getCurrent();
            stockEntity.setCurrent(current - order.getQuantity());
            stockDao.save(stockEntity);
        }
    }


    public void orderStatusUpdated(OrderParticularEntity orderParticular) {
        List<OrderParticularStatusEntity> statusHistory = orderParticular.getStatusHistory();
        OrderParticularStatusType currentStatus = OrderParticularStatusType.valueOf(statusHistory.get(0).getStatus());
        StockEntity stockEntity = stockDao.findByProduct(orderParticular.getProduct()).get(0);
        int current = stockEntity.getCurrent();
        stockEntity.getOrdered();
        switch (currentStatus) {
        case Being_Returned:
            int beingReturned = stockEntity.getBeingReturned();
            stockEntity.setBeingReturned(beingReturned + orderParticular.getQuantity());
            break;
        case Ordered:
            stockEntity.setCurrent(current - orderParticular.getQuantity());
            break;
        case Returned_Defective:
            int returnedDefective = stockEntity.getReturnedDefective();
            stockEntity.setReturnedDefective(returnedDefective + orderParticular.getQuantity());
            if (statusHistoryContains(statusHistory, OrderParticularStatusType.Being_Returned)) {
                beingReturned = stockEntity.getBeingReturned();
                stockEntity.setBeingReturned(beingReturned - orderParticular.getQuantity());
            }
            break;
        case Returned_OK:
            stockEntity.setCurrent(current + orderParticular.getQuantity());
            int returnedOk = stockEntity.getReturnedOK();
            stockEntity.setReturnedOK(returnedOk + orderParticular.getQuantity());
            if (statusHistoryContains(statusHistory, OrderParticularStatusType.Being_Returned)) {
                beingReturned = stockEntity.getBeingReturned();
                stockEntity.setBeingReturned(beingReturned - orderParticular.getQuantity());
            }
        default:
            break;
        }
        stockDao.save(stockEntity);
        
    }


    private boolean statusHistoryContains(List<OrderParticularStatusEntity> statusHistory, OrderParticularStatusType status) {
        for (OrderParticularStatusEntity statusEntity : statusHistory) {
            if (statusEntity.getStatus().equals(status.name())) {
                return true;
            }
        }
        return false;
    }


    public List<Stock> getStocks() {
        Iterable<StockEntity> all = stockDao.findAll();
        List<Stock> stocks = new ArrayList<>();
        for (StockEntity stockEntity : all) {
            Stock stock = stockMapper.createDto(stockEntity);
            stocks.add(stock);
        }
        return stocks;
    }
    
    
}
