package ru.otus.spring.psannikov.docker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.psannikov.docker.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findById(long id);

    List<Author> findAll();
}
