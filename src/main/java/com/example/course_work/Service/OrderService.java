package com.example.course_work.Service;

import com.example.course_work.Config.UserDetail;
import com.example.course_work.Dtos.AddOrderReqest;
import com.example.course_work.Model.OrderOptics;
import com.example.course_work.Model.Product;
import com.example.course_work.Model.User;
import com.example.course_work.Repository.OrderRepository;
import com.example.course_work.Repository.ProductRepository;
import com.example.course_work.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void addOrder(AddOrderReqest orderReqest, Authentication authentication){
        List<Product> orderProducts = new ArrayList<>();
        OrderOptics order = new OrderOptics();

        User user = userRepository.findByEmail(authentication.getName()).get();

        for(Long productId : orderReqest.getProducts()){
            orderProducts.add(productRepository.findById(productId).get());
        }

        order.addProducts(orderProducts);
        order.setUser(user);
        System.out.println(order);
        orderRepository.save(order);

        System.out.println(order);
    }

    public List<OrderOptics> ordersByUserId(Long id, Authentication authentication){
        User user = null;
        System.out.println(id);

        if(id == null){
            user = userRepository.findByEmail(authentication.getName()).get();
        } else{
            user = userRepository.findById(id).get();
        }

        return orderRepository.findByUser(user);
    }
}
