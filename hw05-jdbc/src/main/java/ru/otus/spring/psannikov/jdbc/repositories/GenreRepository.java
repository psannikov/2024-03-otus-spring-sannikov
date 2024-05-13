package ru.otus.spring.psannikov.jdbc.repositories;

import ru.otus.spring.psannikov.jdbc.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findById(long id);
}
