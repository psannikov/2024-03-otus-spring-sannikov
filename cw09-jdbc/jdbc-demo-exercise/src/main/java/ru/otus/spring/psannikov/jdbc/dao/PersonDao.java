package ru.otus.spring.psannikov.jdbc.dao;

import ru.otus.spring.psannikov.jdbc.domain.Person;

import java.util.List;

public interface PersonDao {
    int count();

    void insert(Person person);

    Person getById(long id);

    List<Person> getAll();

    void deleteById(long id);
}
