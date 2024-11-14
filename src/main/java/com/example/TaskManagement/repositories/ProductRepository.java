package com.example.TaskManagement.repositories;


import com.example.TaskManagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByProductNameOrProductId(String productName,Integer productId);
}
