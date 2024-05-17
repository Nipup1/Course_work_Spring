package com.example.course_work.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(unique = true)
    private String email;
    private String password;

    private String name;
    private String lastname;
    private String number;
    private String roles;
}
