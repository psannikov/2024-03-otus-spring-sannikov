package ru.otus.spring.psannikov.hystrix.services;

import ru.otus.spring.psannikov.hystrix.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> findById(long id);

    List<Genre> findAll();
}
