package com.location24x7.ecommerce.inventory.dto.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.location24x7.ecommerce.inventory.dto.Order;
import com.location24x7.ecommerce.inventory.dto.OrderParticular;
import com.location24x7.ecommerce.inventory.dto.OrderStatusType;
import com.location24x7.ecommerce.inventory.dto.SellerPortalType;
import com.location24x7.ecommerce.inventory.model.OrderEntity;
import com.location24x7.ecommerce.inventory.model.OrderParticularEntity;

@Component
public class OrderMapper implements Mapper<OrderEntity, Order> {

    @Autowired
    private OrderParticularMapper opMapper;

    public OrderEntity createEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity(order.getId());
        orderEntity.setInvoiceNo(order.getInvoiceNo());
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setDate(order.getDate());
        Set<OrderParticularEntity> particulars = opMapper.createEntities(order.getParticulars(), orderEntity);
        orderEntity.setParticulars(particulars);
        Double total = 0.0;
        for (OrderParticularEntity orderParticularEntity : particulars) {
            total += orderParticularEntity.getTotalPrice();
        }
        orderEntity.setTotalPrice(total);
        orderEntity.setCustomerAddress(order.getCustomerAddress());
        orderEntity.setCustomerName(order.getCustomerName());
        orderEntity.setFees(order.getFees());
        orderEntity.setRefundDate(order.getRefundDate());
        orderEntity.setSellerPortal(order.getSellerPortal().name());
        orderEntity.setShippingCharges(order.getShippingCharges());
        if (!StringUtils.isEmpty(order.getStatus()))
            orderEntity.setStatus(order.getStatus().name());
        return orderEntity;
    }

    public Order createDto(OrderEntity orderEntity) {
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setInvoiceNo(orderEntity.getInvoiceNo());
        order.setOrderId(orderEntity.getOrderId());
        order.setDate(orderEntity.getDate());
        List<OrderParticular> particulars = opMapper.createDtos(orderEntity.getParticulars());
        order.setParticulars(particulars);
        Double total = 0.0;
        for (OrderParticular particular : particulars) {
            total += particular.getTotalPrice();
        }
        order.setTotalPrice(total);
        order.setCustomerAddress(orderEntity.getCustomerAddress());
        order.setCustomerName(orderEntity.getCustomerName());
        order.setFees(orderEntity.getFees());
        order.setRefundDate(orderEntity.getRefundDate());
        order.setSellerPortal(SellerPortalType.valueOf(orderEntity.getSellerPortal()));
        order.setShippingCharges(orderEntity.getShippingCharges());
        if (!StringUtils.isEmpty(orderEntity.getStatus()))
            order.setStatus(OrderStatusType.valueOf(orderEntity.getStatus()));
        return order;
    }

    public List<Order> createDtos(Iterable<OrderEntity> entities) {
        List<Order> orders = new ArrayList<Order>();
        for (OrderEntity entity : entities) {
            Order order = createDto(entity);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public Set<OrderEntity> createEntities(Iterable<Order> dtos) {
        Set<OrderEntity> entities = new HashSet<OrderEntity>();
        for (Order order : dtos) {
            OrderEntity entity = createEntity(order);
            entities.add(entity);
        }
        return entities;
    }

}
