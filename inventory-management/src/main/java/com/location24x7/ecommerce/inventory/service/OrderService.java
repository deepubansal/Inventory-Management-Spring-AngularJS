package com.location24x7.ecommerce.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dao.OrderDao;
import com.location24x7.ecommerce.inventory.dto.Order;
import com.location24x7.ecommerce.inventory.dto.mapper.OrderMapper;
import com.location24x7.ecommerce.inventory.model.OrderEntity;

@Component
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderMapper mapper;

    public List<Order> getOrders() {
        Iterable<OrderEntity> entities = orderDao.findAll();
        List<Order> orders = mapper.createDtos(entities);
        return orders;
    }

    public Order getOrder(Long orderId) {
        OrderEntity orderEntity = orderDao.findOne(orderId);
        Order order = mapper.createDto(orderEntity);
        return order;
    }

    public Order createOrder(Order order) {
        OrderEntity orderEntity = mapper.createEntity(order);
        orderEntity = orderDao.save(orderEntity);
        orderEntity = orderDao.findOne(orderEntity.getId());
        order = mapper.createDto(orderEntity);
        return order;
    }

}
