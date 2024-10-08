package ru.otus.spring.psannikov.docker.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.psannikov.docker.model.Person;
import ru.otus.spring.psannikov.docker.repository.PersonRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;

    @GetMapping("/api/persons")
    public List<Person> getAllPersons() {
        return this.repository.findAll();
    }
}
