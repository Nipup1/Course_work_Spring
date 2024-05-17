package com.example.course_work.Model;

import lombok.Data;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("glasses_frame")
public class GlassesFrame extends Product{
    private String gender;
    private String modelnumder;

}
