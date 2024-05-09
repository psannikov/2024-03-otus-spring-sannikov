package ru.otus.spring.psannikov.spring.boot.shell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.spring.boot.shell.domain.Student;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final LocalizedIOService ioService;

    @Override
    public Student determineCurrentStudent() {
        var firstName = ioService.readStringWithPromptLocalized("StudentService.input.first.name");
        var lastName = ioService.readStringWithPromptLocalized("StudentService.input.last.name");
        return new Student(firstName, lastName);
    }
}
