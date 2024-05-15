package ru.otus.spring.psannikov.spring.boot.shell.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class TestResultTest {
    private TestResult testResult;
    private final int CLEAR_RESULT_TEST = 0;
    private final int PLUS_ONE_CORRECT_RESULT_TEST = 1;
    Question question = mock(Question.class);
    Student student = mock(Student.class);


    @BeforeEach
    void init() {
        testResult = new TestResult(student);
    }

    @Test
    public void testApplyCorrectAnswer() {
        testResult.applyAnswer(question, true);
        assertEquals(PLUS_ONE_CORRECT_RESULT_TEST,testResult.getRightAnswersCount());
    }

    @Test
    public void testApplyUncorrectAnswer() {
        testResult.applyAnswer(question, false);
        assertEquals(CLEAR_RESULT_TEST,testResult.getRightAnswersCount());
    }
}
