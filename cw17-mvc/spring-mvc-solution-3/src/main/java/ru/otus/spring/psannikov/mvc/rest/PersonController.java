package ru.otus.spring.psannikov.mvc.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.psannikov.mvc.domain.Person;
import ru.otus.spring.psannikov.mvc.repostory.PersonRepository;
import ru.otus.spring.psannikov.mvc.rest.dto.PersonDto;
import ru.otus.spring.psannikov.mvc.rest.exceptions.NotFoundException;

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

    @RequestMapping(value = "/persons", method = RequestMethod.GET, params = "name")
    public PersonDto getPersonByNameInRequest(@RequestParam("name") String name) {
        Person person = repository.findByName(name).stream().findFirst().orElseThrow(NotFoundException::new);
        return PersonDto.toDto(person);
    }

    @GetMapping("/persons/{id}")
    public PersonDto getPersonByIdInPath(@PathVariable("id") long id) {
        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        return PersonDto.toDto(person);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.badRequest().body("Таких тут нет!");
    }
}
