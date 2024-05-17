package com.example.course_work.Controller;

import com.example.course_work.Dtos.FIllterGlassesFrameReqest;
import com.example.course_work.Model.ContactLenses;
import com.example.course_work.Model.GlassesFrame;
import com.example.course_work.Service.GlassesFrameService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/galssesframe")
public class GlassesFrameController {

    private final GlassesFrameService glassesFrameService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public void addGlassesFrame(@RequestBody GlassesFrame glassesFrame){
        glassesFrameService.addGlassesFrame(glassesFrame);
    }

    @GetMapping("/getglassesframe")
    public Optional<GlassesFrame> getGlassesFrameById(@RequestParam Long id){
        return glassesFrameService.findGlassesFrameById(id);
    }

    @PostMapping("/fillter")
    public List<GlassesFrame> fillterGlassesFrame(@RequestBody FIllterGlassesFrameReqest fIllterGlassesFrameReqest){
        return glassesFrameService.fillterGlassesFrame(fIllterGlassesFrameReqest);
    }

    @GetMapping("/findByBrand")
    public List<GlassesFrame> findByBrand(@RequestParam(required = false) String brand){
        return glassesFrameService.findByBrand(brand);
    }
}
