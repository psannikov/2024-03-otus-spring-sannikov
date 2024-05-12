package ru.otus.spring.psannikov.ineritancedemo.repository;

import ru.otus.spring.psannikov.ineritancedemo.model.A;

import java.util.List;

public interface ARepository {
    List<A> findAll();
    void save(A a);
}
