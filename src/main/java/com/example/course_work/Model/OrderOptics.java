package com.example.course_work.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class OrderOptics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderopticsid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToMany@JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "orderopticsid"),
            inverseJoinColumns = @JoinColumn(name = "productid"))
    private List<Product> products = new ArrayList<>();

    public void addProducts(List<Product> products){
        this.products.addAll(products);
    }
}
