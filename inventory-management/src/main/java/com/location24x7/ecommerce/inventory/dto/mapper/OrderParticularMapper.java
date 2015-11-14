package com.location24x7.ecommerce.inventory.dto.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dto.OrderParticular;
import com.location24x7.ecommerce.inventory.model.ProductEntity;
import com.location24x7.ecommerce.inventory.model.OrderEntity;
import com.location24x7.ecommerce.inventory.model.OrderParticularEntity;

@Component
public class OrderParticularMapper implements Mapper<OrderParticularEntity, OrderParticular> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public OrderParticularEntity createEntity(OrderParticular dto) {
        return createEntity(dto, null);
    }

    public OrderParticularEntity createEntity(OrderParticular dto, OrderEntity orderEntity) {
        OrderParticularEntity entity = new OrderParticularEntity();
        entity.setProduct(new ProductEntity(dto.getProduct().getId()));
        entity.setOrder(orderEntity);
        entity.setQuantity(dto.getQuantity());
        entity.setTotalPrice(dto.getTotalPrice());
        return entity;
    }

    @Override
    public OrderParticular createDto(OrderParticularEntity e) {
        OrderParticular particular = new OrderParticular();
        particular.setProduct(productMapper.createDto(e.getProduct()));
        particular.setQuantity(e.getQuantity());
        particular.setTotalPrice(e.getTotalPrice());
        return particular;
    }

    @Override
    public List<OrderParticular> createDtos(Iterable<OrderParticularEntity> entities) {
        List<OrderParticular> dtos = new ArrayList<OrderParticular>();
        for (OrderParticularEntity ppEntity : entities) {
            OrderParticular dto = createDto(ppEntity);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Set<OrderParticularEntity> createEntities(Iterable<OrderParticular> dtos) {
        return createEntities(dtos, null);
    }

    public Set<OrderParticularEntity> createEntities(Iterable<OrderParticular> dtos, OrderEntity orderEntity) {
        Set<OrderParticularEntity> entities = new HashSet<OrderParticularEntity>();
        for (OrderParticular orderParticular : dtos) {
            OrderParticularEntity entity = createEntity(orderParticular, orderEntity);
            entities.add(entity);
        }
        return entities;
    }
}
