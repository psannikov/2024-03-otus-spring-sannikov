package ru.otus.spring.psannikov.spring.security.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.psannikov.spring.security.auth.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findById(long id);

    List<Genre> findAll();
}
