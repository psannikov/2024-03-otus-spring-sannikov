package ru.otus.spring.psannikov.spring.boot.shell.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.psannikov.spring.boot.shell.config.AppProperties;
import ru.otus.spring.psannikov.spring.boot.shell.config.TestFileNameProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class CsvQuestionDaoTest {
    @MockBean
    private AppProperties appProperties;
    @MockBean
    private TestFileNameProvider fileNameProvider;
    private CsvQuestionDao csvQuestionDao;

    final static String QUESTIONS_FILE_NAME_TEST = "questions.csv";
    final static String QUESTION = "How many letters are in the Russian alphabet?";

    @Test
    void testGetFileFromResourceAsStream ()
    {

    }

    @Test
    void testFindAll() {
        when (fileNameProvider.getTestFileName()).thenReturn(QUESTIONS_FILE_NAME_TEST);
        var listQuestions = csvQuestionDao.findAll();
        System.out.println(listQuestions.size());
//        assertEquals(QUESTION, listQuestions.getFirst().text());
    }
}
