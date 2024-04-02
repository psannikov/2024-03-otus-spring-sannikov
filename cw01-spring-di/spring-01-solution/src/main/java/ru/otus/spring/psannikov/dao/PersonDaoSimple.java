package ru.otus.spring.psannikov.dao;

import ru.otus.spring.psannikov.domain.Person;

public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}
