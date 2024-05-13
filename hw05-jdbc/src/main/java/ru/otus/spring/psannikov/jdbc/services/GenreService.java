package ru.otus.spring.psannikov.jdbc.services;

import ru.otus.spring.psannikov.jdbc.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
