package ru.otus.spring.psannikov.hw01.xml.config.service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.psannikov.hw01.xml.config.dao.QuestionDao;
import ru.otus.spring.psannikov.hw01.xml.config.domain.Answer;
import ru.otus.spring.psannikov.hw01.xml.config.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao csvQuestionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        List<Question> list = csvQuestionDao.findAll();
        for (Question question : list) {
            System.out.println("=".repeat(10) + "Question" + "=".repeat(10));
            System.out.println(question.text());
            for (Answer answer : question.answers()) {
                System.out.println("-".repeat(10) + "Answer" + "-".repeat(10));
                System.out.println(answer.text());
                System.out.println("Is correct: " + answer.isCorrect());
            }
            System.out.println("=".repeat(28));
        }
    }
}
