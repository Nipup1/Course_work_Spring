package com.example.course_work.Controller;

import com.example.course_work.Dtos.FIllterGlassesFrameReqest;
import com.example.course_work.Dtos.FillterContactLensesReqest;
import com.example.course_work.Model.ContactLenses;
import com.example.course_work.Model.GlassesFrame;
import com.example.course_work.Service.ContactLenseService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contactlenses")
public class ContactLensesController {

    private final ContactLenseService contactLenseService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public void addContactLenses(@RequestBody ContactLenses contactLenses){
        contactLenseService.addContactLenses(contactLenses);
    }

    @GetMapping("/getcontactlenses")
    public Optional<ContactLenses> getContactLensesById(@RequestParam Long id){
        return contactLenseService.findContactLensesById(id);
    }

    @PostMapping("/fillter")
    public List<ContactLenses> fillterContactLenses(@RequestBody FillterContactLensesReqest fillterContactLensesReqest){
        return contactLenseService.fillterContactLenses(fillterContactLensesReqest);
    }

    @GetMapping("/findByBrand")
    public List<ContactLenses> findByBrand(@RequestParam(required = false) String brand){
        return contactLenseService.findByBrand(brand);
    }
}
