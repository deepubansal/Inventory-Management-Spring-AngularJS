package com.location24x7.ecommerce.inventory.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.location24x7.ecommerce.inventory.dao.OrderDao;
import com.location24x7.ecommerce.inventory.dao.OrderParticularDao;
import com.location24x7.ecommerce.inventory.dto.EventType;
import com.location24x7.ecommerce.inventory.dto.Order;
import com.location24x7.ecommerce.inventory.dto.OrderParticular;
import com.location24x7.ecommerce.inventory.dto.OrderParticularStatusType;
import com.location24x7.ecommerce.inventory.dto.OrderStatusType;
import com.location24x7.ecommerce.inventory.dto.mapper.OrderMapper;
import com.location24x7.ecommerce.inventory.model.OrderEntity;
import com.location24x7.ecommerce.inventory.model.OrderParticularEntity;
import com.location24x7.ecommerce.inventory.model.OrderParticularStatusEntity;

@Component
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderParticularDao orderParticularDao;
    
    @Autowired
    private StockService stockService;
    
    @Autowired
    private OrderMapper mapper;

    public List<Order> getOrders() {
        Iterable<OrderEntity> entities = orderDao.findAll();
        List<Order> orders = mapper.createDtos(entities);
        for (Order order : orders) {
            inferOrderStatus(order);
        }
        return orders;
    }

    public Order getOrder(Long orderId) {
        OrderEntity orderEntity = orderDao.findOne(orderId);
        Order order = mapper.createDto(orderEntity);
        inferOrderStatus(order);
        return order;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Order createOrder(Order order) {
        order = saveOrUpdateOrder(order);
        stockService.orderRecieved(mapper.createEntity(order));
        return order;
    }

  
    public Order saveOrUpdateOrder(Order order) {
        inferOrderStatus(order);
        OrderEntity orderEntity = mapper.createEntity(order);
        orderEntity = orderDao.save(orderEntity);
        orderEntity = orderDao.findOne(orderEntity.getId());
        order = mapper.createDto(orderEntity);
        return order;
    }

    private void inferOrderStatus(Order order) {
        List<OrderParticular> particulars = order.getParticulars();
        order.setStatus(null);
        for (OrderParticular orderParticular : particulars) {
            if (orderParticular.getStatus() == null) {
                orderParticular.setStatus(OrderParticularStatusType.Ordered);
            }
            if (orderParticular.getStatus() != OrderParticularStatusType.Returned_OK 
                    || orderParticular.getStatus() != OrderParticularStatusType.Returned_Defective) {
                order.setStatus(OrderStatusType.Inprogress);
            }
        }
        if (order.getStatus() == null)
            order.setStatus(OrderStatusType.Completed);
    }

    public Order updateOrder(Long id, Order order) {
        order.setId(id);
        order = saveOrUpdateOrder(order);
        return order;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Order newEvent(Long id, EventType eventType, String reason) {
        OrderParticularEntity orderParticular = orderParticularDao.findOne(id);
        OrderParticularStatusEntity orderStatusEntity = new OrderParticularStatusEntity();
        orderStatusEntity.setOrder(orderParticular);
        orderStatusEntity.setReason(reason);
        orderStatusEntity.setStatus(getNewStatus(eventType, orderParticular.getStatusHistory()).name());
        orderParticular.getStatusHistory().add(orderStatusEntity);
        orderParticular = orderParticularDao.save(orderParticular);
        stockService.orderStatusUpdated(orderParticular);
        return mapper.createDto(orderParticular.getOrder());
    }

    public Set<EventType> getAllowedEvents(Long id) {
            OrderParticularEntity orderParticular = orderParticularDao.findOne(id);
            List<OrderParticularStatusEntity> statusHistory = orderParticular.getStatusHistory();
            Set<EventType> allowedEvents = getAllowedEvents(statusHistory);
            return allowedEvents;
    }
    private Set<EventType> getAllowedEvents(List<OrderParticularStatusEntity> statusHistory) {
        EventType[] values = EventType.values();
        Set<EventType> allowedEvents = new HashSet<>();
        for (EventType eventType : values) {
            if (isValidEvent(eventType, statusHistory))
                allowedEvents.add(eventType);
        }
        return allowedEvents;
    }

    private boolean isValidEvent(EventType eventType, List<OrderParticularStatusEntity> statusHistory) {
        try {
            getNewStatus(eventType, statusHistory);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    private OrderParticularStatusType getNewStatus(EventType eventType, List<OrderParticularStatusEntity> statusHistory) {
        if (statusHistory.size() == 0) {
            if (eventType != EventType.CONFIRMED_ORDER)
                throw new RuntimeException("Event not allowed.");
            else
                return OrderParticularStatusType.Ordered;
        }
        OrderParticularStatusType lastStatus = 
                OrderParticularStatusType.valueOf(statusHistory.get(0).getStatus());
        switch (eventType) {
        case CONFIRMED_ORDER:
            throw new RuntimeException("Event not allowed.");
        case RETURN_REQUESTED:
            switch (lastStatus) {
            case Being_Returned:
            case Returned_OK:
            case Returned_Defective:
                throw new RuntimeException("Event not allowed.");
            case Ordered:
                return OrderParticularStatusType.Being_Returned;
            }
        case RETURNED_DEFECTIVE:
            switch (lastStatus) {
            case Returned_OK:
            case Returned_Defective:
                throw new RuntimeException("Event not allowed.");
            case Being_Returned:
            case Ordered:
                return OrderParticularStatusType.Returned_Defective;
            }
        case RETURNED_OK:
            switch (lastStatus) {
            case Returned_OK:
            case Returned_Defective:
                throw new RuntimeException("Event not allowed.");
            case Being_Returned:
            case Ordered:
                return OrderParticularStatusType.Returned_OK;
            }
        case CANCELLED:
            switch (lastStatus) {
            case Being_Returned:
            case Returned_Defective:
            case Returned_OK:
                throw new RuntimeException("Event not allowed.");
            case Ordered:
                return OrderParticularStatusType.Being_Returned;
            }
        }
        throw new RuntimeException("Event not allowed.");
    }
    

}
