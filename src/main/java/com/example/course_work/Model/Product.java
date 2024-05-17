package com.example.course_work.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "product_type")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

    private String photo;
    private BigDecimal cost;
    private String brand;
}
