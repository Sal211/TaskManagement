package com.example.TaskManagement.repositories;


import com.example.TaskManagement.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespository extends JpaRepository<Customers,Integer> {


}
