package ru.otus.spring.psannikov.mvc.ajax.repostory;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.mvc.ajax.domain.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAll();
}
