package ru.otus.spring.psannikov.react.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.psannikov.react.repostory.PersonRepository;
import ru.otus.spring.psannikov.react.rest.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/persons")
    public List<PersonDto> getAllPersons() {
        return repository.findAll().stream().map(PersonDto::toDto)
                .collect(Collectors.toList());
    }
}
