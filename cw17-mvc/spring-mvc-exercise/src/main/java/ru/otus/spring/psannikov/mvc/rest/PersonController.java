package ru.otus.spring.psannikov.mvc.rest;

import ru.otus.spring.psannikov.mvc.repostory.PersonRepository;


public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }
}
