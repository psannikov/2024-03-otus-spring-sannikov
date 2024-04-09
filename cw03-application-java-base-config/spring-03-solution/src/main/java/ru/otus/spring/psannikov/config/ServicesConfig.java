package ru.otus.spring.psannikov.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.psannikov.dao.PersonDao;
import ru.otus.spring.psannikov.service.PersonService;
import ru.otus.spring.psannikov.service.PersonServiceImpl;

@Configuration
public class ServicesConfig {

    @Bean
    public PersonService personService(@Qualifier("personDaoSmart") PersonDao dao) {
        return new PersonServiceImpl(dao);
    }
}
