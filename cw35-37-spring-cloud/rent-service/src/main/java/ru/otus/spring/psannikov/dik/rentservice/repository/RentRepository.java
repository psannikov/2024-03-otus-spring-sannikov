package ru.otus.spring.psannikov.dik.rentservice.repository;

import ru.otus.spring.psannikov.dik.rentservice.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findRentsByAnimal(String animal);
}
