package ru.otus.spring.psannikov.dao;

import ru.otus.demo.domain.Person;

public interface PersonDao {

	Person findByName(String name);
}
