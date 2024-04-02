package ru.otus.spring.psannikov.hw01.xml.config.dao;

import ru.otus.spring.psannikov.hw01.xml.config.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
