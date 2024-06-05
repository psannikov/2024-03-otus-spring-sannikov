package ru.otus.spring.psannikov.data.nosql.services;

import ru.otus.spring.psannikov.data.nosql.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> findById(String id);

    List<Genre> findAll();
}
