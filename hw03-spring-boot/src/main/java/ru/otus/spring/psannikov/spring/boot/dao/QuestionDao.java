package ru.otus.spring.psannikov.spring.boot.dao;

import ru.otus.spring.psannikov.spring.boot.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
