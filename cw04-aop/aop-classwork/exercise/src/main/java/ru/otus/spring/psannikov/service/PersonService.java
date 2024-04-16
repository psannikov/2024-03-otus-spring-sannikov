package ru.otus.spring.psannikov.service;

import ru.otus.spring.psannikov.domain.Person;

public interface PersonService {

	Person getByName(String name);
}
