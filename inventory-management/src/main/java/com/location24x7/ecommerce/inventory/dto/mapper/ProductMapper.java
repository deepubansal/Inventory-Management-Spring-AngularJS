package com.location24x7.ecommerce.inventory.dto.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.location24x7.ecommerce.inventory.dto.Product;
import com.location24x7.ecommerce.inventory.model.ProductEntity;

@Component
public class ProductMapper implements Mapper<ProductEntity, Product> {

    @Override
    public ProductEntity createEntity(Product product) {
        ProductEntity productEntity = new ProductEntity(product.getId());
        productEntity.setBrand(product.getBrand());
        productEntity.setCategory(product.getCategory());
        productEntity.setColour(product.getColour());
        productEntity.setDescription(product.getDescription());
        productEntity.setName(product.getName());
        productEntity.setSize(product.getSize());
        productEntity.setIdentifiers(StringUtils.arrayToCommaDelimitedString(product.getIdentifiers()));
        return productEntity;
    }

    @Override
    public Product createDto(ProductEntity productEntity) {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setBrand(productEntity.getBrand());
        product.setCategory(productEntity.getCategory());
        product.setColour(productEntity.getColour());
        product.setDescription(productEntity.getDescription());
        product.setName(productEntity.getName());
        product.setSize(productEntity.getSize());
        if (!StringUtils.isEmpty(productEntity.getIdentifiers()))
            product.setIdentifiers(productEntity.getIdentifiers().split(","));
        return product;
    }

    @Override
    public List<Product> createDtos(Iterable<ProductEntity> entities) {
        List<Product> products = new ArrayList<Product>();
        for (ProductEntity entity : entities) {
            Product product = createDto(entity);
            products.add(product);
        }
        return products;
    }

    @Override
    public Set<ProductEntity> createEntities(Iterable<Product> dtos) {
        Set<ProductEntity> entities = new HashSet<ProductEntity>();
        for (Product product : dtos) {
            entities.add(createEntity(product));
        }
        return entities;
    }
}
