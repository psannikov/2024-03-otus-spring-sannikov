package ru.otus.spring.psannikov.mvc.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.psannikov.mvc.repostory.PersonRepository;
import ru.otus.spring.psannikov.mvc.rest.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<PersonDto> getAllPersons() {
        return repository.findAll().stream()
                .map(PersonDto::toDto)
                .collect(Collectors.toList());
    }
}
