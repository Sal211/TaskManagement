package com.example.TaskManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TblPayment", schema = "dbo")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    Integer paymentId;

    @Column(name = "PaymentDate")
    LocalDateTime paymentDate;

    @Column(name = "Amount")
    BigDecimal amount;

    @Column(name = "PaymentStatus")
    String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "PaymentMethodID",foreignKey = @ForeignKey(name = "FK_tblPayment_PaymentMethod"))
    PaymentMethod paymentMethod;

    @JsonIgnore
    @OneToMany(mappedBy = "payment")
    private List<OrderDetail> orderDetails;

    @PrePersist
    private void setPaymentDate() {
        if (this.paymentDate == null) {
            this.paymentDate = LocalDateTime.now();
        }
    }
}
