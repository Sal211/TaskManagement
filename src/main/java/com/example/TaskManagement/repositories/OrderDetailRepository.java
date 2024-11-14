package com.example.TaskManagement.repositories;


import com.example.TaskManagement.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {

}
