package ru.otus.spring.psannikov.view.repostory;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.view.domain.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAll();
}
