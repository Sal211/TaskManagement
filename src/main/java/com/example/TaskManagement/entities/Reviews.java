package com.example.TaskManagement.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TblReviews", schema = "dbo")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "ProductID", foreignKey = @ForeignKey(name = "FK_Reviews_Products"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CustomerID", foreignKey = @ForeignKey(name = "FK_Reviews_Customers"))
    private Customers customer;

    @Column(name = "Rating")
    Integer rating;

    @Column(name = "ReviewText")
    String reviewText;

    @Column(name = "ReviewDate")
    LocalDateTime reviewDate;
}
