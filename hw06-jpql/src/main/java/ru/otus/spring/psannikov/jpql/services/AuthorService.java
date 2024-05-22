package ru.otus.spring.psannikov.jpql.services;

import ru.otus.spring.psannikov.jpql.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
