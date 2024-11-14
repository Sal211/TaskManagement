package com.example.TaskManagement.repositories;

import com.example.TaskManagement.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("SELECT o FROM Order o WHERE o.orderId = :orderId")
    Optional<Order> order(@Param("orderId") String orderId);

}
