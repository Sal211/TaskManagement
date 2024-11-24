package com.example.TaskManagement.dtos;

import com.example.TaskManagement.entities.Category;
import com.example.TaskManagement.entities.OrderDetail;
import com.example.TaskManagement.entities.Reviews;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    String productName;
    String description;
    BigDecimal price;
    Integer stockQuantity;
    Integer categoryId;
}
