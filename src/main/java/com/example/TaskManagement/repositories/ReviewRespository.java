package com.example.TaskManagement.repositories;

import com.example.TaskManagement.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRespository extends JpaRepository<Reviews,Integer> {
}
