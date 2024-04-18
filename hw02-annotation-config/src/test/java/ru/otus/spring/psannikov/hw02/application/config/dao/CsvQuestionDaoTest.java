package ru.otus.spring.psannikov.hw02.application.config.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.application.config.config.TestFileNameProvider;
import ru.otus.spring.hw02.application.config.dao.CsvQuestionDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CsvQuestionDaoTest {

    private final TestFileNameProvider fileNameProvider = mock(TestFileNameProvider.class);
    private CsvQuestionDao csvQuestionDao;
    final static String QUESTIONS_FILE_NAME_TEST = "questions.csv";
    final static String QUESTION = "How many letters are in the Russian alphabet?";

    @BeforeEach
    void  init() {
        csvQuestionDao = new CsvQuestionDao(fileNameProvider);
    }

    @Test
    void testFindAll() {
        when (fileNameProvider.getTestFileName()).thenReturn(QUESTIONS_FILE_NAME_TEST);
        var listQuestions = csvQuestionDao.findAll();
        assertEquals(QUESTION, listQuestions.getFirst().text());
    }
}
