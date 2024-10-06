package ru.otus.spring.psannikov.dik.rentservice.service;

import ru.otus.spring.psannikov.dik.rentservice.model.Rent;

import java.util.List;

public interface RentService {

    List<Rent> getRentsByAnimal(String animal);
}
