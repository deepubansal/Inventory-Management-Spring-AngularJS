package com.location24x7.ecommerce.inventory.domain;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.location24x7.ecommerce.inventory.model.ProductEntity;

@Component
public class ProductMapper {

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

    public Product createDomain(ProductEntity productEntity) {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setBrand(productEntity.getBrand());
        product.setCategory(productEntity.getCategory());
        product.setColour(productEntity.getColour());
        product.setDescription(productEntity.getDescription());
        product.setName(productEntity.getName());
        product.setSize(productEntity.getSize());
        product.setIdentifiers(productEntity.getIdentifiers().split(","));
        return product;
    }

    
    
}
