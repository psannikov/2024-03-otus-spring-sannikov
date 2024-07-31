package ru.otus.spring.psannikov.data.rest.services;

import ru.otus.spring.psannikov.data.rest.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> findById(long id);

    List<Genre> findAll();
}
