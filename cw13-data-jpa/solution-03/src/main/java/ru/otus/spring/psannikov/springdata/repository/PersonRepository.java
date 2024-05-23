package ru.otus.spring.psannikov.springdata.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.psannikov.springdata.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @EntityGraph(attributePaths = "email")
    List<Person> findAll();

    Optional<Person> findByName(String s);

    Optional<Person> findByEmailAddress(String email);
}
