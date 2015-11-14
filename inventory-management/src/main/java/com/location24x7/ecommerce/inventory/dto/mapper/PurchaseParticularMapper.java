package com.location24x7.ecommerce.inventory.dto.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dto.PurchaseParticular;
import com.location24x7.ecommerce.inventory.model.ProductEntity;
import com.location24x7.ecommerce.inventory.model.PurchaseEntity;
import com.location24x7.ecommerce.inventory.model.PurchaseParticularEntity;

@Component
public class PurchaseParticularMapper implements Mapper<PurchaseParticularEntity, PurchaseParticular> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PurchaseParticularEntity createEntity(PurchaseParticular dto) {
        return createEntity(dto, null);
    }

    public PurchaseParticularEntity createEntity(PurchaseParticular dto, PurchaseEntity purchaseEntity) {
        PurchaseParticularEntity entity = new PurchaseParticularEntity();
        entity.setProduct(new ProductEntity(dto.getProduct().getId()));
        entity.setPurchase(purchaseEntity);
        entity.setQuantity(dto.getQuantity());
        entity.setTotalCost(dto.getTotalCost());
        return entity;
    }

    @Override
    public PurchaseParticular createDto(PurchaseParticularEntity e) {
        PurchaseParticular particular = new PurchaseParticular();
        particular.setProduct(productMapper.createDto(e.getProduct()));
        particular.setQuantity(e.getQuantity());
        particular.setTotalCost(e.getTotalCost());
        return particular;
    }

    @Override
    public List<PurchaseParticular> createDtos(Iterable<PurchaseParticularEntity> entities) {
        List<PurchaseParticular> dtos = new ArrayList<PurchaseParticular>();
        for (PurchaseParticularEntity ppEntity : entities) {
            PurchaseParticular dto = createDto(ppEntity);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Set<PurchaseParticularEntity> createEntities(Iterable<PurchaseParticular> dtos) {
        return createEntities(dtos, null);
    }

    public Set<PurchaseParticularEntity> createEntities(Iterable<PurchaseParticular> dtos, PurchaseEntity purchaseEntity) {
        Set<PurchaseParticularEntity> entities = new HashSet<PurchaseParticularEntity>();
        for (PurchaseParticular purchaseParticular : dtos) {
            PurchaseParticularEntity entity = createEntity(purchaseParticular, purchaseEntity);
            entities.add(entity);
        }
        return entities;
    }
}
