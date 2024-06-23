package ru.otus.spring.psannikov.react.repostory;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.react.domain.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();
}
