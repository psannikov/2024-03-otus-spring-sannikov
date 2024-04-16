package ru.otus.spring.psannikov.service;

import ru.otus.demo.domain.Person;

public interface PersonService {

	Person getByName(String name);
}
