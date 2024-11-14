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
@Table(name = "TblCustomers", schema = "dbo")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    Integer customerId;

    @Column(name = "FirstName")
    String firstName;

    @Column(name = "LastName")
    String lastName;

    @Column(name = "Email")
    String email;

    @Column(name = "PhoneNumber")
    String phoneNumber;

    @Column(name = "Address")
    String address;

    @Column(name = "City")
    String city;

    @Column(name = "PostalCode")
    String postalCode;

    @Column(name = "Country")
    String country;

    @Column(name = "CreatedDate")
    LocalDateTime createdDate;

    @Column(name = "Balance")
    BigDecimal balance;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Reviews> reviews;
}
