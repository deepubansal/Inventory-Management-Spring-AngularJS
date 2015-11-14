package com.location24x7.ecommerce.inventory.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dto.Purchase;
import com.location24x7.ecommerce.inventory.service.PurchaseService;

@Path("/api/purchase")
@Component
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Purchase> getPurchases() {
        return purchaseService.getPurchases();
    }

    @GET
    @Path("/{purchaseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Purchase getPurchase(@PathParam("purchaseId") Long purchaseId) {
        return purchaseService.getPurchase(purchaseId);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Purchase createPurchase(Purchase purchase) {
        return purchaseService.createPurchase(purchase);
    }

}
