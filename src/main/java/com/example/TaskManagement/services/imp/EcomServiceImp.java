package com.example.TaskManagement.services.imp;


import com.example.TaskManagement.dtos.OrderDto;
import com.example.TaskManagement.dtos.ProductDto;
import com.example.TaskManagement.dtos.projections.RevenueProjection;
import com.example.TaskManagement.dtos.projections.SellProjection;
import com.example.TaskManagement.entities.Product;
import com.example.TaskManagement.services.EcomService;
import com.example.TaskManagement.services.imp.otherService.OrderService;
import com.example.TaskManagement.services.imp.otherService.ProductService;
import com.example.TaskManagement.utils.BaseRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class EcomServiceImp implements EcomService {
    private final OrderService orderService;
    private final ProductService productService;

    @Override
    public BaseRespone<Void> orderProduct(OrderDto orderDto) {
       return orderService.orderProduct(orderDto);
    }

    @Override
    public BaseRespone<Void> cancelOrder(OrderDto orderDto) {
        return orderService.cancelOrder(orderDto);
    }

    @Override
    public BaseRespone<List<SellProjection>> getTopSell() {
        return orderService.getTopSell();
    }

    @Override
    public BaseRespone<List<SellProjection>> getLeastSell() {
        return orderService.getLeastSell();
    }

    @Override
    public BaseRespone<List<Map<String,Object>>> getRevenue() {
        return orderService.getRevenue();
    }

    @Override
    public CompletableFuture<BaseRespone<Product>> getProductByNameOrId(String name, Integer Id) {
        return productService.getProductByNameOrId(name,Id);
    }

    @Override
    public BaseRespone<List<Product>> getAllProduct() {
        return productService.getAllProduct();
    }

    @Override
    public BaseRespone<List<Product>> getProductByCategory(Integer Id) {
        return productService.getProductByCategory(Id);
    }

    @Override
    public BaseRespone<String> saveProduct(ProductDto productdto) {
        return productService.saveProduct(productdto);
    }

    @Override
    public BaseRespone<String> updateProduct(Integer Id, ProductDto productdto) {
        return productService.updateProduct(Id,productdto);
    }
}
