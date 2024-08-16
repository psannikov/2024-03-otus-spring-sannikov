package ru.otus.spring.psannikov.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.psannikov.docker.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
