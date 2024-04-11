package ru.otus.spring.hw02.application.config.service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw02.application.config.dao.QuestionDao;
import ru.otus.spring.hw02.application.config.domain.Student;
import ru.otus.spring.hw02.application.config.domain.TestResult;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question: questions) {
            var isAnswerValid = false; // Задать вопрос, получить ответ
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }
}
