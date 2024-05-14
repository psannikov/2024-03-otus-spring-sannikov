package ru.otus.spring.psannikov.jpql_demo.repositories;

import ru.otus.spring.psannikov.jpql_demo.models.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
}
