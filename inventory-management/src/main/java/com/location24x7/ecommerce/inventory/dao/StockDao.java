package com.location24x7.ecommerce.inventory.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.location24x7.ecommerce.inventory.model.ProductEntity;
import com.location24x7.ecommerce.inventory.model.StockEntity;

@Repository
public interface StockDao extends CrudRepository<StockEntity, Long> {

    public List<StockEntity> findByProduct(ProductEntity product);

}
