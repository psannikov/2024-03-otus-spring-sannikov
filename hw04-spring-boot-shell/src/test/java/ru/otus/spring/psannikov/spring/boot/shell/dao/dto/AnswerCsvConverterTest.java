package ru.otus.spring.psannikov.spring.boot.shell.dao.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.psannikov.spring.boot.shell.domain.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("В SpringBootTest конвертор в ответ и признак правильности. ")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
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
    void shouldConvertStrToAnswer() {
        Object answer = answerCsvConverter.convertToRead(row);
        assertEquals(new Answer(answerText,answerBoolean), answer);
    }
}
