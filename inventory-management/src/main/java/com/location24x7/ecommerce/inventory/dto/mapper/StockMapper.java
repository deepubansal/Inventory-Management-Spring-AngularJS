package com.location24x7.ecommerce.inventory.dto.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dto.Stock;
import com.location24x7.ecommerce.inventory.model.StockEntity;

@Component
public class StockMapper implements Mapper<StockEntity, Stock> {

    @Autowired
    private ProductMapper productMapper;
    
    @Override
    public StockEntity createEntity(Stock stock) {
        StockEntity stockEntity = new StockEntity(stock.getId());
        return stockEntity;
    }

    @Override
    public Stock createDto(StockEntity stockEntity) {
        Stock stock = new Stock();
        stock.setId(stockEntity.getId());
        stock.setCurrent(stockEntity.getCurrent());
        stock.setBeingReturned(stockEntity.getBeingReturned());
        stock.setOrdered(stockEntity.getOrdered());
        stock.setProduct(productMapper.createDto(stockEntity.getProduct()));
        stock.setPurchased(stockEntity.getPurchased());
        stock.setReturnedOk(stockEntity.getReturnedOK());
        stock.setReturnedDefective(stockEntity.getReturnedDefective());
        return stock;
    }

    @Override
    public List<Stock> createDtos(Iterable<StockEntity> entities) {
        List<Stock> stocks = new ArrayList<Stock>();
        for (StockEntity entity : entities) {
            Stock stock = createDto(entity);
            stocks.add(stock);
        }
        return stocks;
    }

    @Override
    public Set<StockEntity> createEntities(Iterable<Stock> dtos) {
        Set<StockEntity> entities = new HashSet<StockEntity>();
        for (Stock stock : dtos) {
            entities.add(createEntity(stock));
        }
        return entities;
    }
}
