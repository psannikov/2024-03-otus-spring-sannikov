package ru.otus.spring.psannikov.dao;

import ru.otus.spring.domain.Person;

public interface PersonDao {

	Person findByName(String name);
}
