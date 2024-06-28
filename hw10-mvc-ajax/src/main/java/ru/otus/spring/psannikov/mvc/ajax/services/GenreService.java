package ru.otus.spring.psannikov.mvc.ajax.services;

import ru.otus.spring.psannikov.mvc.ajax.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> findById(long id);

    List<Genre> findAll();
}
