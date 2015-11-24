package com.location24x7.ecommerce.inventory.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.location24x7.ecommerce.inventory.dto.Stock;
import com.location24x7.ecommerce.inventory.service.StockService;

@Path("/api/stocks")
@Component
public class StocksController {

    @Autowired
    private StockService stockService;
    
    @GET
    @Path("/latest")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stock> getStocks() {
        List<Stock> stocks = stockService.getStocks();
        return stocks;
    }
    
}
