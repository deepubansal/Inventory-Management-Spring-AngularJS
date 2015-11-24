package com.location24x7.ecommerce.inventory.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.location24x7.ecommerce.inventory.model.OrderParticularEntity;

@Repository
public interface OrderParticularDao extends CrudRepository<OrderParticularEntity, Long> {
}
