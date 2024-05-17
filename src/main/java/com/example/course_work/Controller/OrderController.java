package com.example.course_work.Controller;

import com.example.course_work.Dtos.AddOrderReqest;
import com.example.course_work.Model.OrderOptics;
import com.example.course_work.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public void addOrder(@RequestBody AddOrderReqest orderReqest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        orderService.addOrder(orderReqest, authentication);
    }

    @GetMapping("/ordersByUserId")
    public List<OrderOptics> ordesByUserId(@RequestParam(required = false) Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return orderService.ordersByUserId(id, authentication);
    }
}
