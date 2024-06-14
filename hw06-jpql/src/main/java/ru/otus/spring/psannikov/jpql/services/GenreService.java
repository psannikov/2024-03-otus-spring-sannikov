package ru.otus.spring.psannikov.jpql.services;

import ru.otus.spring.psannikov.jpql.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
