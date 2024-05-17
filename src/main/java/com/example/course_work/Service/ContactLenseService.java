package com.example.course_work.Service;

import com.example.course_work.Dtos.FillterContactLensesReqest;
import com.example.course_work.Model.ContactLenses;
import com.example.course_work.Repository.ContactLenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactLenseService {
    private final ContactLenseRepository contactLenseRepository;

    public void addContactLenses(ContactLenses contactLenses){
        contactLenseRepository.save(contactLenses);
    }

    public List<ContactLenses> fillterContactLenses(FillterContactLensesReqest fillter){
        List<ContactLenses> filteredProducts = contactLenseRepository.findAll();
        System.out.println(filteredProducts);
        System.out.println(fillter);

        if (fillter.getBrand() != null) {
            filteredProducts.retainAll(contactLenseRepository.findAllByBrand(fillter.getBrand()));
        }

        if (fillter.getMinPrice() != null && fillter.getMaxPrice() != null) {
            filteredProducts.retainAll(contactLenseRepository.findAllByCostBetween(fillter.getMinPrice(), fillter.getMaxPrice()));
        }

        if (fillter.getWearingmode() != null) {
            filteredProducts.retainAll(contactLenseRepository.findAllByWearingmode(fillter.getWearingmode()));
        }

        if (fillter.getMinopticalpower() != null && fillter.getMaxopticalpower() != null) {
            filteredProducts.retainAll(contactLenseRepository.findAllByOpticalpowerBetween(fillter.getMinopticalpower(), fillter.getMaxopticalpower()));
        }

        return filteredProducts;
    }

    public Optional<ContactLenses> findContactLensesById(Long id){
        return contactLenseRepository.findById(id);
    }

    public List<ContactLenses> findByBrand(String title) {
        List<ContactLenses> products;
        if (title != null) {
            products = contactLenseRepository.findByBrandContaining(title);
        } else {
            products = contactLenseRepository.findAll();
        }
        return products;
    }
}
