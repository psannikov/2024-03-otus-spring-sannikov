package ru.otus.spring.psannikov.spring.boot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.spring.boot.dao.QuestionDao;
import ru.otus.spring.psannikov.spring.boot.domain.Answer;
import ru.otus.spring.psannikov.spring.boot.domain.Student;
import ru.otus.spring.psannikov.spring.boot.domain.Question;
import ru.otus.spring.psannikov.spring.boot.domain.TestResult;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printLineLocalized("TestService.answer.the.questions");
        ioService.printLine("");

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
