package ru.otus.spring.psannikov.dao;

import ru.otus.spring.psannikov.domain.Person;

public class PersonDaoSmart implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 21);
    }
}
