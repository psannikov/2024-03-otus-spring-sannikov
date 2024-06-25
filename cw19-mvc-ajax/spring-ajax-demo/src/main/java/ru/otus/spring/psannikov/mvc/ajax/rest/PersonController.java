package ru.otus.spring.psannikov.mvc.ajax.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.psannikov.mvc.ajax.repostory.PersonRepository;
import ru.otus.spring.psannikov.mvc.ajax.rest.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonRepository repository;

    @GetMapping("/api/persons")
    public List<PersonDto> getAllPersons() {
        return repository.findAll().stream().map(PersonDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/persons")
    public PersonDto addPerson(@RequestBody PersonDto personDto) {
        var savedPerson = repository.save(personDto.toDomainObject());
        return PersonDto.toDto(savedPerson);
    }
}
