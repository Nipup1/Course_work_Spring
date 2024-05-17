package com.example.course_work.Service;

import com.example.course_work.Dtos.FIllterGlassesFrameReqest;
import com.example.course_work.Model.ContactLenses;
import com.example.course_work.Model.GlassesFrame;
import com.example.course_work.Repository.GlassesFrameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GlassesFrameService {

    private final GlassesFrameRepository glassesFrameRepository;

    public void addGlassesFrame(GlassesFrame glassesFrame){
        glassesFrameRepository.save(glassesFrame);
    }

    public List<GlassesFrame> fillterGlassesFrame(FIllterGlassesFrameReqest fillter){
        List<GlassesFrame> filteredProducts = glassesFrameRepository.findAll();

        if (fillter.getBrand() != null) {
            filteredProducts.retainAll(glassesFrameRepository.findAllByBrand(fillter.getBrand()));
        }

        if (fillter.getMinPrice() != null && fillter.getMaxPrice() != null) {
            filteredProducts.retainAll(glassesFrameRepository.findAllByCostBetween(fillter.getMinPrice(), fillter.getMaxPrice()));
        }

        if (fillter.getGender() != null) {
            filteredProducts.retainAll(glassesFrameRepository.findAllByGender(fillter.getGender()));
        }

        if (fillter.getModelnumder() != null) {
            filteredProducts.retainAll(glassesFrameRepository.findAllByModelnumder(fillter.getModelnumder()));
        }

        return filteredProducts;
    }

    public Optional<GlassesFrame> findGlassesFrameById(Long id){
        return glassesFrameRepository.findById(id);
    }

    public List<GlassesFrame> findByBrand(String title) {
        List<GlassesFrame> products;
        if (title != null) {
            products = glassesFrameRepository.findByBrandContaining(title);
        } else {
            products = glassesFrameRepository.findAll();
        }
        return products;
    }
}
