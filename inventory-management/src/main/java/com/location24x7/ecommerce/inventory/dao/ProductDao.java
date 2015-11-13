package com.location24x7.ecommerce.inventory.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.location24x7.ecommerce.inventory.model.ProductEntity;

@Repository
public interface ProductDao extends CrudRepository<ProductEntity, Long> {

    public List<ProductEntity> findByCategory(String category);

    public List<ProductEntity> findByBrand(String brand);
}
