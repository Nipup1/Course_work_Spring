package com.example.course_work.Model;


import lombok.*;
import jakarta.persistence.*;


import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("contact_lenses")
public class ContactLenses extends Product{
    private BigDecimal opticalpower;
    private String wearingmode;
}
