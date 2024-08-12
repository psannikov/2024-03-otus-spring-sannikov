package ru.otus.spring.psannikov.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.psannikov.spring.batch.model.PersonJpa;

@Repository
public interface PersonRepository extends JpaRepository<PersonJpa, Long> {
}
