package com.example.TaskManagement.services;

import com.example.TaskManagement.dtos.OrderDto;
import com.example.TaskManagement.dtos.ProductDto;
import com.example.TaskManagement.dtos.projections.RevenueProjection;
import com.example.TaskManagement.dtos.projections.SellProjection;
import com.example.TaskManagement.entities.Product;
import com.example.TaskManagement.utils.BaseRespone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public interface EcomService {

    // ORDER PRODUCT
    BaseRespone<Void> orderProduct(OrderDto orderDto);

    // CANCEL ORDER
    BaseRespone<Void> cancelOrder(OrderDto orderDto);

    // GET TOP SELL
    BaseRespone<List<SellProjection>> getTopSell();

    // GET THE LEAST SELL
    BaseRespone<List<SellProjection>> getLeastSell();

    // GET REVENUE
    BaseRespone<List<Map<String,Object>>> getRevenue();

    // GET PRODUCT BY NAME OR ID
    CompletableFuture<BaseRespone<Product>> getProductByNameOrId(String name, Integer Id);

    // GET ALL PRODUCT
    BaseRespone<List<Product>> getAllProduct();

    // GET PRODUCT BY CATEGORY
    BaseRespone<List<Product>> getProductByCategory(Integer Id);

    // SAVE PRODUCT
    BaseRespone<String> saveProduct(ProductDto productdto);

    // UPDATE PRODUCT
    BaseRespone<String> updateProduct(Integer Id,ProductDto productdto);

}
