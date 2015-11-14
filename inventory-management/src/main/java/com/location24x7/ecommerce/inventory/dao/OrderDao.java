package com.location24x7.ecommerce.inventory.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.location24x7.ecommerce.inventory.model.OrderEntity;

@Repository
public interface OrderDao extends CrudRepository<OrderEntity, Long> {

    public List<OrderEntity> findByInvoiceNo(String invoiceNo);

    public List<OrderEntity> findByOrderId(String orderId);
}
