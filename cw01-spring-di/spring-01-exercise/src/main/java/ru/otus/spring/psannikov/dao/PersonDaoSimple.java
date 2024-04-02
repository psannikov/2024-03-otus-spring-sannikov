package ru.otus.spring.psannikov.dao;

import ru.otus.spring.psannikov.domain.Person;

public class PersonDaoSimple implements PersonDao {
    private int defaultAge = 18;

    public void setDefaultAge(int defaultAge) {
        this.defaultAge = defaultAge;
    }

    public Person findByName(String name) {
        return new Person(name, defaultAge);
    }
}
