package com.example.TaskManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TblOrders", schema = "dbo")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    Integer orderId;

    @Column(name = "OrderDate")
    LocalDateTime orderDate;

    @ManyToOne 
    @JoinColumn(name = "CustomerID",foreignKey = @ForeignKey(name = "FK_Orders_Customers"))
    Customers customer;

    @Column(name = "OrderStatus")
    String orderStatus;

    @Column(name = "ShippingDate")
    LocalDateTime shippingDate;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
