package com.location24x7.ecommerce.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dao.ProductDao;
import com.location24x7.ecommerce.inventory.dto.Product;
import com.location24x7.ecommerce.inventory.dto.mapper.ProductMapper;
import com.location24x7.ecommerce.inventory.model.ProductEntity;

@Component
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductMapper mapper;

    public List<Product> getProducts() {
        Iterable<ProductEntity> entities = productDao.findAll();
        List<Product> products = mapper.createDtos(entities);
        return products;
    }

    public Product getProduct(Long productId) {
        ProductEntity productEntity = productDao.findOne(productId);
        Product product = mapper.createDto(productEntity);
        return product;
    }

    public Product createProduct(Product product) {
        ProductEntity productEntity = mapper.createEntity(product);
        productEntity = productDao.save(productEntity);
        productEntity = productDao.findOne(productEntity.getId());
        product = mapper.createDto(productEntity);
        return product;
    }
}
