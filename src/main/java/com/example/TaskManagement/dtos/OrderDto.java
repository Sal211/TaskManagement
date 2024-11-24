package com.example.TaskManagement.dtos;

import com.example.TaskManagement.entities.Customers;
import com.example.TaskManagement.entities.OrderDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    Integer paymentId;
    Integer orderId;
    Integer customerId;
    Integer productId;
    Integer paymentMethodId;
    Integer quantity;
    BigDecimal unitPrice;
    float discount;
    BigDecimal totalPriceOrder;
}
