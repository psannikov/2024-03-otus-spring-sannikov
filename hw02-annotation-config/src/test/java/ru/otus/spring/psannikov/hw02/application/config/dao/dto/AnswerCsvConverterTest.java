package ru.otus.spring.psannikov.hw02.application.config.dao.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.application.config.dao.dto.AnswerCsvConverter;
import ru.otus.spring.hw02.application.config.domain.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerCsvConverterTest {
    AnswerCsvConverter answerCsvConverter;
    String answerText = "It is correct answer";
    Boolean answerBoolean = true;
    String row;

    @BeforeEach
    void init() {
        answerCsvConverter = new AnswerCsvConverter();
        row = answerText + "%" + answerBoolean;
    }

    @Test
    void convertToRead() {
        Object answer = answerCsvConverter.convertToRead(row);
        assertEquals(new Answer(answerText,answerBoolean), answer);
    }
}
