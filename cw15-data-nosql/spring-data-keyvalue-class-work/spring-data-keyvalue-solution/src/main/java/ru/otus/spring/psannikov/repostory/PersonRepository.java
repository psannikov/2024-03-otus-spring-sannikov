package ru.otus.spring.psannikov.repostory;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.otus.spring.psannikov.domain.Person;

import java.util.List;

public interface PersonRepository extends KeyValueRepository<Person, Integer> {

    Person save(Person person);

    List<Person> findAll();
}
