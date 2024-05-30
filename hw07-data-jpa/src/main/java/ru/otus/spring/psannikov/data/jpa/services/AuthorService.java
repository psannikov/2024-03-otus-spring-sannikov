package ru.otus.spring.psannikov.data.jpa.services;

import ru.otus.spring.psannikov.data.jpa.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
