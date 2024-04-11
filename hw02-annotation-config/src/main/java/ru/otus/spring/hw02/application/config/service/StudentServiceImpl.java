package ru.otus.spring.hw02.application.config.service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw02.application.config.domain.Student;

@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    @Override
    public Student determineCurrentStudent() {
        var firstName = ioService.readStringWithPrompt("Please input your first name");
        var lastName = ioService.readStringWithPrompt("Please input your last name");
        return new Student(firstName, lastName);
    }
}
