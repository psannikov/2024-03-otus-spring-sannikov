package ru.otus.spring.psannikov.spring.boot.service;

import ru.otus.spring.psannikov.spring.boot.domain.Student;
import ru.otus.spring.psannikov.spring.boot.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
