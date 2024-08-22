package ru.otus.spring.psannikov.dik.rentservice.controller;

import ru.otus.spring.psannikov.dik.rentservice.model.Rent;
import ru.otus.spring.psannikov.dik.rentservice.service.RentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rents")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping(value = "/animal/{animal}")
    public List<Rent> getRents(@PathVariable String animal) {
        return rentService.getRentsByAnimal(animal);
    }
}
