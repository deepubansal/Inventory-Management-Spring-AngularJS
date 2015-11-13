package com.location24x7.ecommerce.inventory.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dao.ProductDao;
import com.location24x7.ecommerce.inventory.domain.Product;
import com.location24x7.ecommerce.inventory.domain.ProductMapper;
import com.location24x7.ecommerce.inventory.model.ProductEntity;

@Path("/api/product")
@Component
public class ProductController {

    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private ProductMapper mapper;
    
    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("productId") Long productId) {
        ProductEntity productEntity = productDao.findOne(productId);
        Product product = mapper.createDomain(productEntity);
        return product;
    }
    
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product createProduct(Product product) {
        ProductEntity productEntity = mapper.createEntity(product);
        productEntity = productDao.save(productEntity);
        product = mapper.createDomain(productEntity);
        return product;
    }

    
}
