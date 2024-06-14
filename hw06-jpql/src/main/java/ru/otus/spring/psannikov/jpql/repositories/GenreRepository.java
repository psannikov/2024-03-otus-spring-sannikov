package ru.otus.spring.psannikov.jpql.repositories;

import ru.otus.spring.psannikov.jpql.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findById(long id);
}
