package com.example.TaskManagement.services.imp.otherService;

import com.example.TaskManagement.dtos.ProductDto;
import com.example.TaskManagement.entities.OrderDetail;
import com.example.TaskManagement.entities.Product;
import com.example.TaskManagement.exceptions.BusinessException;
import com.example.TaskManagement.repositories.CategoryRepository;
import com.example.TaskManagement.repositories.OrderDetailRepository;
import com.example.TaskManagement.repositories.ProductRepository;
import com.example.TaskManagement.repositories.ReviewRespository;
import com.example.TaskManagement.utils.BaseRespone;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ReviewRespository reviewRespository;

    @Async
    public CompletableFuture<BaseRespone<Product>> getProductByNameOrId(String name, Integer Id) {
        BaseRespone<Product> response = new BaseRespone<>();
        try {
            // GET PRODUCT
            var Product = productRepository.findByProductNameOrProductId(name, Id);
            if(Product.isPresent()){
                throw new Exception ("Product Not Found");
            }

            // RESPONSE
            response.setStatus("success");
            response.setData(Product.get());
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

    public BaseRespone<List<Product>> getProductByCategory(Integer Id){
        BaseRespone<List<Product>> response = new BaseRespone<>();

        try{
            // FIND PRODUCT
            List<Product> products = productRepository.findByCategory_CategoryId(Id);
            if(products.isEmpty()){
                throw new Exception ("Product Not Found");
            }

            // RESPONSE
            response.setStatus("success");
            response.setData(products);
            return response;
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    public BaseRespone<String> saveProduct(ProductDto productdto){
        BaseRespone<String> response = new BaseRespone<>();
        try{
            Product product = new Product();

            // FIND CATEGORY
            var category = categoryRepository.findById(productdto.getCategoryId());
            if(!category.isPresent()){
                throw new Exception ("Category Not Found");
            }

            // SAVE PRODUCT
            BeanUtils.copyProperties(productdto,product);
            product.setCategory(category.get());
            productRepository.save(product);

            // RESPONSE
            response.setStatus("success");
            response.setData("Insert Success");
            return response;
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    public BaseRespone<String> updateProduct(Integer Id,ProductDto productdto){
        BaseRespone<String> response = new BaseRespone<>();
        try{
            // FIND CATEGORY
            var category = categoryRepository.findById(productdto.getCategoryId());
            if(!category.isPresent()){
                throw new Exception ("Category Not Found");
            }

            // FIND PRODUCT
            var existProduct = productRepository.findById(Id);
            if(!existProduct.isPresent()){
                throw new Exception ("Product Not Found");
            }

            // UPDATE PRODUCT
            BeanUtils.copyProperties(productdto,existProduct);
            existProduct.get().setCategory(category.get());
            productRepository.save(existProduct.get());

            // RESPONSE
            response.setStatus("success");
            response.setData("Update Success");
            return response;
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
}
