package ru.otus.spring.psannikov.spring.boot.shell.dao.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("В SpringBootTest конвертор в вопрос и список ответов. ")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class QuestionDtoTest {
    @Test
    public void shouldConvertToQuestion()
    {

    }
}
