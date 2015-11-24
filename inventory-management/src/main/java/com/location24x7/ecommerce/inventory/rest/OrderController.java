package com.location24x7.ecommerce.inventory.rest;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dto.EventType;
import com.location24x7.ecommerce.inventory.dto.Order;
import com.location24x7.ecommerce.inventory.dto.OrderUpdate;
import com.location24x7.ecommerce.inventory.service.OrderService;

@Path("/api/order")
@Component
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("id") Long id) {
        return orderService.getOrder(id);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Order createOrder(Order order) {
        return orderService.createOrder(order);
    }

    @POST
    @Path("/{id}/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Order updateOrder(@PathParam("id") Long id, OrderUpdate order) {
        return null;
    }
    
    @POST
    @Path("/particular/{id}/event/{eventType}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Order newEvent(@PathParam("id") Long id, @PathParam("eventType") EventType eventType,
            String reason) {
        return orderService.newEvent(id, eventType, reason);
    }
    
    @GET
    @Path("/particular/{id}/allowedEvents")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<EventType> getAllowedEvents(@PathParam("id") Long id) {
        return orderService.getAllowedEvents(id);
    }
    
    
    
    
}
