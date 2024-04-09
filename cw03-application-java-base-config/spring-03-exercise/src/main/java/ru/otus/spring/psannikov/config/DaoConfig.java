package ru.otus.spring.psannikov.config;

import ru.otus.spring.psannikov.dao.PersonDao;
import ru.otus.spring.psannikov.dao.PersonDaoSimple;

public class DaoConfig {

    public PersonDao personDaoSimple() {
        return new PersonDaoSimple();
    }
}
