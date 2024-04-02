package ru.otus.spring.psannikov.service;

import ru.otus.spring.psannikov.dao.PersonDao;
import ru.otus.spring.psannikov.domain.Person;

public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
