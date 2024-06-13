package ru.otus.spring.psannikov.mvc.repostory;

import org.springframework.data.repository.ListCrudRepository;
import ru.otus.spring.psannikov.mvc.domain.Person;

import java.util.List;

public interface PersonRepository extends ListCrudRepository<Person, Long> {

    List<Person> findAll();
    List<Person> findByName(String name);
}
