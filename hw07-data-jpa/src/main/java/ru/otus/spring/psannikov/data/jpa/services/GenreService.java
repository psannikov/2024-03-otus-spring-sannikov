package ru.otus.spring.psannikov.data.jpa.services;

import ru.otus.spring.psannikov.data.jpa.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
