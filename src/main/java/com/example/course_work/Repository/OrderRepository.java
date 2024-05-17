package com.example.course_work.Repository;

import com.example.course_work.Model.OrderOptics;
import com.example.course_work.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderOptics, Long> {
    List<OrderOptics> findByUser(User user);
}
