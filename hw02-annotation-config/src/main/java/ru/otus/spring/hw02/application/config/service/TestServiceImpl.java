package ru.otus.spring.hw02.application.config.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.application.config.dao.QuestionDao;
import ru.otus.spring.hw02.application.config.domain.Answer;
import ru.otus.spring.hw02.application.config.domain.Question;
import ru.otus.spring.hw02.application.config.domain.Student;
import ru.otus.spring.hw02.application.config.domain.TestResult;

@Service
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
            var answer = ioService.readStringWithPrompt(question.text());
            if (answer.equals(getCorrectAnswer(question))) {
                isAnswerValid = true;
            }
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }

    public String getCorrectAnswer(Question question) {
        for (Answer answer : question.answers()) {
            if (answer.isCorrect()) {
                return answer.text();
            }
        }
        return null;
    }
}
