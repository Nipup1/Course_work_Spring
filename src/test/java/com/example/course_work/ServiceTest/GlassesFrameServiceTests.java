package com.example.course_work.ServiceTest;

import com.example.course_work.Dtos.FIllterGlassesFrameReqest;
import com.example.course_work.Model.GlassesFrame;
import com.example.course_work.Repository.GlassesFrameRepository;
import com.example.course_work.Service.GlassesFrameService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class GlassesFrameServiceTests {

    @Mock
    private GlassesFrameRepository glassesFrameRepository;

    @InjectMocks
    private GlassesFrameService glassesFrameService;

    @Test
    public void testAddGlassesFrame() {
        GlassesFrame glassesFrame = new GlassesFrame();
        glassesFrameService.addGlassesFrame(glassesFrame);
        Mockito.verify(glassesFrameRepository, Mockito.times(1)).save(glassesFrame);
    }

    @Test
    public void testFillterGlassesFrame() {
        FIllterGlassesFrameReqest filter = new FIllterGlassesFrameReqest();
        List<GlassesFrame> expectedFilteredProducts = new ArrayList<>();
        expectedFilteredProducts.add(new GlassesFrame());
        Mockito.when(glassesFrameRepository.findAll()).thenReturn(expectedFilteredProducts);

        List<GlassesFrame> filteredProducts = glassesFrameService.fillterGlassesFrame(filter);

        Assert.assertEquals(expectedFilteredProducts, filteredProducts);

        Mockito.verify(glassesFrameRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindGlassesFrameById() {
        Long id = 1L;
        GlassesFrame expectedGlassesFrame = new GlassesFrame();
        Mockito.when(glassesFrameRepository.findById(id)).thenReturn(Optional.of(expectedGlassesFrame));

        Optional<GlassesFrame> result = glassesFrameService.findGlassesFrameById(id);

        Assert.assertEquals(Optional.of(expectedGlassesFrame), result);

        Mockito.verify(glassesFrameRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void testFindByBrand() {
        String brand = "Ray-Ban";
        List<GlassesFrame> expectedProducts = new ArrayList<>();
        expectedProducts.add(new GlassesFrame());
        Mockito.when(glassesFrameRepository.findByBrandContaining(brand)).thenReturn(expectedProducts);

        List<GlassesFrame> products = glassesFrameService.findByBrand(brand);

        Assert.assertEquals(expectedProducts, products);

        Mockito.verify(glassesFrameRepository, Mockito.times(1)).findByBrandContaining(brand);
    }
}