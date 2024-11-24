package com.example.TaskManagement.controllers;

import com.example.TaskManagement.dtos.OrderDto;
import com.example.TaskManagement.dtos.ProductDto;
import com.example.TaskManagement.dtos.projections.RevenueProjection;
import com.example.TaskManagement.dtos.projections.SellProjection;
import com.example.TaskManagement.entities.Product;
import com.example.TaskManagement.repositories.PaymentRepository;
import com.example.TaskManagement.services.EcomService;
import com.example.TaskManagement.utils.BaseRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/ABC")
@RequiredArgsConstructor
public class EcomContoller {
    private final EcomService ecomService;
    private final PaymentRepository paymentRepository;
    @Value("${device-model}")
    String deviceModel;

    @GetMapping("/ping")
    public String ping(){
        return "Ping Ecom";
    }

    @PostMapping("/order")
    public BaseRespone<Void> orderProduct(@RequestBody OrderDto orderDto) {
        return ecomService.orderProduct(orderDto);
    }

    @PostMapping("/cancelOrder")
    public BaseRespone<Void> cancelOrder(@RequestBody OrderDto orderDto) {
        try {
            return ecomService.cancelOrder(orderDto);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return ecomService.cancelOrder(orderDto);
        }
    }

    @GetMapping("/getTopSell")
    public BaseRespone<List<SellProjection>> getTopSell() {
        return ecomService.getTopSell();
    }

    @GetMapping("/getLeastSell")
    public BaseRespone<List<SellProjection>> getLeastSell() {
        return ecomService.getLeastSell();
    }

    @GetMapping("/getRevenue")
    public BaseRespone<List<Map<String,Object>>> getRevenue() {
        return ecomService.getRevenue();
    }

    @GetMapping("/getProduct/{name}/{id}")
    public CompletableFuture<BaseRespone<Product>> getProductByNameOrId(@PathVariable("name") String name,@PathVariable("id") Integer Id) {
        return ecomService.getProductByNameOrId(name,Id);
    }

    @GetMapping("/getAllProduct")
    public BaseRespone<List<Product>> getAllProduct() {
        return ecomService.getAllProduct();
    }

    @GetMapping("/getProduct/{id}")
    public BaseRespone<List<Product>> getProductByCategory(@PathVariable("id") Integer Id) {
        return ecomService.getProductByCategory(Id);
    }

    @PostMapping("/saveProduct")
    public BaseRespone<String> saveProduct(@RequestBody ProductDto productdto) {
        return ecomService.saveProduct(productdto);
    }

    @PostMapping("/updateProduct/{id}")
    public BaseRespone<String> updateProduct(@PathVariable("id") Integer Id,@RequestBody ProductDto productdto) {
        return ecomService.updateProduct(Id,productdto);
    }

}
