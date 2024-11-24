package com.example.TaskManagement.repositories;


import com.example.TaskManagement.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByProductNameOrProductId(String productName,Integer productId);
    List<Product> findByCategory_CategoryId(Integer categoryId);



}
