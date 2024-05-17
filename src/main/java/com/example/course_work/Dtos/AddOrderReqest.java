package com.example.course_work.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddOrderReqest {
    private List<Long> products;
}
