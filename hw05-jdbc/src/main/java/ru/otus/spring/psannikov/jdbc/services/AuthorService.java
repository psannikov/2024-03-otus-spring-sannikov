package ru.otus.spring.psannikov.jdbc.services;

import ru.otus.spring.psannikov.jdbc.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
