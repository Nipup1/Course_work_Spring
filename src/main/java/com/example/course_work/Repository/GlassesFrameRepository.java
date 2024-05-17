package com.example.course_work.Repository;

import com.example.course_work.Model.GlassesFrame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface GlassesFrameRepository extends JpaRepository<GlassesFrame, Long> {
    List<GlassesFrame> findAllByBrand(String brand);
    List<GlassesFrame> findAllByGender(String gender);
    List<GlassesFrame> findAllByModelnumder(String modelnumber);
    List<GlassesFrame> findAllByCostBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<GlassesFrame> findByBrandContaining(String brand);
}
