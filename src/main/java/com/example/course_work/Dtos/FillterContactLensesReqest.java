package com.example.course_work.Dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FillterContactLensesReqest {
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal minopticalpower;
    private BigDecimal maxopticalpower;
    private String brand;
    private String wearingmode;
}
