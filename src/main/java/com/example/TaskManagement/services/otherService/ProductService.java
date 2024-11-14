package com.example.TaskManagement.services.otherService;

import com.example.TaskManagement.entities.Product;
import com.example.TaskManagement.exceptions.BusinessException;
import com.example.TaskManagement.repositories.ProductRepository;
import com.example.TaskManagement.utils.BaseRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Async
    public CompletableFuture<BaseRespone<Product>> getProductByNameOrId(String name, Integer Id) {
        BaseRespone<Product> response = new BaseRespone<>();
        try {
            // GET PRODUCT
            var Product = productRepository.findByProductNameOrProductId(name, Id);
            if(Product.isPresent()){
                response.setStatus("success");
                response.setData(Product.get());
            }

            // RESPONSE
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public BaseRespone<List<Product>> getAllProduct(){
        BaseRespone<List<Product>> response = new BaseRespone<>();
        try {
            // GET PRODUCT
            var products = productRepository.findAll()
                    .stream()
                    .sorted((a,b) -> Integer.compare(a.getProductId(),b.getProductId()))
                    .collect(Collectors.toList());

            // RESPONSE
            response.setData(products);
            response.setStatus("success");
            return response;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

}
