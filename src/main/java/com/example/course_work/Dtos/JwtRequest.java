package com.example.course_work.Dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}
