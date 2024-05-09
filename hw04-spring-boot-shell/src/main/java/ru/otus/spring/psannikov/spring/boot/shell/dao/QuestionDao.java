package ru.otus.spring.psannikov.spring.boot.shell.dao;

import ru.otus.spring.psannikov.spring.boot.shell.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
