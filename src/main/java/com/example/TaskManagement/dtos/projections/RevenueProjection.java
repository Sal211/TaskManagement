package com.example.TaskManagement.dtos.projections;

import java.math.BigDecimal;


public interface RevenueProjection {
    BigDecimal getTotal();
    int getSold();
}

