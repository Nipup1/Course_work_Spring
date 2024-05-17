package com.example.course_work.ServiceTest;

import com.example.course_work.Dtos.FillterContactLensesReqest;
import com.example.course_work.Model.ContactLenses;
import com.example.course_work.Repository.ContactLenseRepository;
import com.example.course_work.Service.ContactLenseService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ContactLenseServiceTests {

    @Mock
    private ContactLenseRepository contactLenseRepository;

    @InjectMocks
    private ContactLenseService contactLenseService;

    @Test
    public void testAddContactLenses() {
        ContactLenses contactLenses = new ContactLenses();
        contactLenseService.addContactLenses(contactLenses);
        Mockito.verify(contactLenseRepository, Mockito.times(1)).save(contactLenses);
    }

    @Test
    public void testFillterContactLenses() {
        FillterContactLensesReqest filter = new FillterContactLensesReqest();
        List<ContactLenses> expectedFilteredProducts = new ArrayList<>();
        expectedFilteredProducts.add(new ContactLenses());
        Mockito.when(contactLenseRepository.findAll()).thenReturn(expectedFilteredProducts);

        List<ContactLenses> filteredProducts = contactLenseService.fillterContactLenses(filter);

        Assert.assertEquals(expectedFilteredProducts, filteredProducts);

        Mockito.verify(contactLenseRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindContactLensesById() {
        Long id = 1L;
        ContactLenses expectedContactLenses = new ContactLenses();
        Mockito.when(contactLenseRepository.findById(id)).thenReturn(Optional.of(expectedContactLenses));

        Optional<ContactLenses> result = contactLenseService.findContactLensesById(id);

        Assert.assertEquals(Optional.of(expectedContactLenses), result);

        Mockito.verify(contactLenseRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void testFindByBrand() {
        String brand = "Acuvue";
        List<ContactLenses> expectedProducts = new ArrayList<>();
        expectedProducts.add(new ContactLenses());
        Mockito.when(contactLenseRepository.findByBrandContaining(brand)).thenReturn(expectedProducts);

        List<ContactLenses> products = contactLenseService.findByBrand(brand);

        Assert.assertEquals(expectedProducts, products);

        Mockito.verify(contactLenseRepository, Mockito.times(1)).findByBrandContaining(brand);
    }
}
