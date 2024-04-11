package ru.otus.spring.hw02.application.config.service;

import ru.otus.spring.hw02.application.config.domain.Student;
import ru.otus.spring.hw02.application.config.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
