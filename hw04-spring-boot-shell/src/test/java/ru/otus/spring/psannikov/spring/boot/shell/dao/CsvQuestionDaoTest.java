package ru.otus.spring.psannikov.spring.boot.shell.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.psannikov.spring.boot.shell.config.AppProperties;
import ru.otus.spring.psannikov.spring.boot.shell.config.TestFileNameProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class CsvQuestionDaoTest {
    private AppProperties appProperties;
    private TestFileNameProvider fileNameProvider;
    private CsvQuestionDao csvQuestionDao;

    final static String QUESTIONS_FILE_NAME_TEST = "questions.csv";
    final static String QUESTION = "How many letters are in the Russian alphabet?";

    @BeforeEach
    void init() {
        appProperties = mock(AppProperties.class);
        fileNameProvider = mock(TestFileNameProvider.class);
        csvQuestionDao = new CsvQuestionDao(appProperties, fileNameProvider);
    }

    @Test
    void testGetFileFromResourceAsStream ()
    {

    }

    @Test
    void testFindAll() {
        when (fileNameProvider.getTestFileName()).thenReturn(QUESTIONS_FILE_NAME_TEST);
        var listQuestions = csvQuestionDao.findAll();
        assertEquals(QUESTION, listQuestions.get(0).text());
    }
}
