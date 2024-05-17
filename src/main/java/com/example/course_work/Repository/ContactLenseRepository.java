package com.example.course_work.Repository;

import com.example.course_work.Model.ContactLenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ContactLenseRepository extends JpaRepository<ContactLenses, Long> {
    List<ContactLenses> findAllByBrand(String brand);
    List<ContactLenses> findAllByWearingmode(String wearingmode);
    List<ContactLenses> findAllByCostBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<ContactLenses> findAllByOpticalpowerBetween(BigDecimal minopticalpower, BigDecimal maxopticalpower);
    List<ContactLenses> findByBrandContaining(String title);
}
