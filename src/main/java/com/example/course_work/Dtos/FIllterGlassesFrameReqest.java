package com.example.course_work.Dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FIllterGlassesFrameReqest {
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String brand;
    private String gender;
    private String modelnumder;
}
