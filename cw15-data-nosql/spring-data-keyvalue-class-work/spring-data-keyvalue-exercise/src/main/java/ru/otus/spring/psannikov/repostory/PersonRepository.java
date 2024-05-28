package ru.otus.spring.psannikov.repostory;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.domain.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();
}
