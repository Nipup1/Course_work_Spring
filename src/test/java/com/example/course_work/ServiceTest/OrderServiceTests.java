package com.example.course_work.ServiceTest;

import com.example.course_work.Dtos.AddOrderReqest;
import com.example.course_work.Model.OrderOptics;
import com.example.course_work.Model.Product;
import com.example.course_work.Model.User;
import com.example.course_work.Repository.OrderRepository;
import com.example.course_work.Repository.ProductRepository;
import com.example.course_work.Repository.UserRepository;
import com.example.course_work.Service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testAddOrder() {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("username");

        AddOrderReqest orderRequest = new AddOrderReqest(List.of(1L, 2L));

        Mockito.when(userRepository.findByEmail(Mockito.any(String.class))).thenReturn(Optional.of(new User()));
        Mockito.when(productRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(new Product()));

        orderService.addOrder(orderRequest, authentication);

        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(OrderOptics.class));
    }

    @Test
    public void testOrdersByUserId() {
        Long id = 1L;
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("username");

        @SuppressWarnings("unchecked")
        List<OrderOptics> expectedOrders = Mockito.mock(List.class);
        Mockito.when(orderRepository.findByUser(Mockito.any(User.class))).thenReturn(expectedOrders);
        Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(new User()));

        List<OrderOptics> orders = orderService.ordersByUserId(id, authentication);

        Assert.assertEquals(expectedOrders, orders);

        Mockito.verify(orderRepository, Mockito.times(1)).findByUser(Mockito.any(User.class));
    }
}