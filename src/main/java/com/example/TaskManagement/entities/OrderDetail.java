package com.example.TaskManagement.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TblOrderDetail", schema = "dbo")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailID")
    Integer orderDetailId;

    @ManyToOne
    @JoinColumn(name = "OrderID", foreignKey = @ForeignKey(name = "FK_OrderDetail_Order"))
    Order order;

    @ManyToOne
    @JoinColumn(name = "ProductID", foreignKey = @ForeignKey(name = "FK_OrderDetail_Product"))
    Product product;

    @ManyToOne
    @JoinColumn(name = "PaymentID", foreignKey = @ForeignKey(name = "FK_OrderDetail_Payment"))
    Payment payment;

    @Column(name = "Quantity")
    Integer quantity;

    @Column(name = "UnitPrice")
    BigDecimal unitPrice;

    @Column(name = "Discount")
    float discount;

    @Column(name = "TotalPrice")
    BigDecimal totalPrice;
}
