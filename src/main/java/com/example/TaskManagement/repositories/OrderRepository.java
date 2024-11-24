package com.example.TaskManagement.repositories;

import com.example.TaskManagement.dtos.projections.RevenueProjection;
import com.example.TaskManagement.dtos.projections.SellProjection;
import com.example.TaskManagement.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(
            value = """ 
                SELECT ISNULL(Sum(TotalPrice),0) AS total,Count(*) AS sold FROM TblOrderDetail AS o\s
                        INNER JOIN tblPayment AS p ON o.PaymentID = p.PaymentID
                        WHERE CONVERT(Date,p.PaymentDate) = CONVERT(date,GetDate()) AND p.PaymentStatus = 'Completed'
                        UNION ALL
                        SELECT ISNULL(Sum(TotalPrice),0) AS total,Count(*) AS sold FROM TblOrderDetail AS o\s
                        INNER JOIN tblPayment AS p ON o.PaymentID = p.PaymentID
                        WHERE MONTH(p.PaymentDate) = MONTH(GetDate()) AND p.PaymentStatus = 'Completed'
            """,nativeQuery = true)
    List<RevenueProjection> getRevenue();

    @Query(
            value = """
                    SELECT TOP 3  pro.ProductName as productName,SUM(od.Quantity) as sold FROM TblOrderDetail AS od\s
                    INNER JOIN TblProducts AS pro ON pro.ProductID = od.ProductID\s
                    GROUP BY pro.ProductName
                    ORDER BY sold DESC
                    """,nativeQuery = true
    )
    List<SellProjection> getTopSell();

    @Query(
            value = """
                    SELECT TOP 10 pro1.ProductName as productName,ISNULL(SUM(od1.Quantity),0) as sold FROM TblOrderDetail AS od1\s
                    INNER JOIN TblProducts AS pro1 ON pro1.ProductID = od1.ProductID\s
                    GROUP BY pro1.ProductName
                    UNION
                    SELECT pro2.ProductName, 0 AS sold FROM tblProducts AS pro2
                    WHERE ProductID NOT IN (SELECT ProductID FROM TblOrderDetail)\s
                    ORDER BY sold ASC
                    """,nativeQuery = true
    )
    List<SellProjection> getleastSell();
}
