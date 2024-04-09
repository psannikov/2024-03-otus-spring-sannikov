package ru.otus.spring.psannikov.config;

import ru.otus.spring.psannikov.dao.PersonDao;
import ru.otus.spring.psannikov.service.PersonService;
import ru.otus.spring.psannikov.service.PersonServiceImpl;

public class ServicesConfig {

    public PersonService personService(PersonDao dao) {
        return new PersonServiceImpl(dao);
    }
}
