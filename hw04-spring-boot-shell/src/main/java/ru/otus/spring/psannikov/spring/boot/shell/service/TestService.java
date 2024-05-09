package ru.otus.spring.psannikov.spring.boot.shell.service;

import ru.otus.spring.psannikov.spring.boot.shell.domain.Student;
import ru.otus.spring.psannikov.spring.boot.shell.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
