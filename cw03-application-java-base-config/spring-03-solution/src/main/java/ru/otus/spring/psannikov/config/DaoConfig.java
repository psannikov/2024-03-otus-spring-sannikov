package ru.otus.spring.psannikov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.psannikov.dao.PersonDao;
import ru.otus.spring.psannikov.dao.PersonDaoSimple;
import ru.otus.spring.psannikov.dao.PersonDaoSmart;

@Configuration
public class DaoConfig {

    @Bean
    public PersonDao personDaoSimple() {
        return new PersonDaoSimple();
    }

    @Bean
    public PersonDao personDaoSmart() {
        return new PersonDaoSmart();
    }
}
