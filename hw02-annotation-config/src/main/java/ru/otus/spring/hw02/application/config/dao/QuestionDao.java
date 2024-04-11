package ru.otus.spring.hw02.application.config.dao;

import ru.otus.spring.hw02.application.config.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
