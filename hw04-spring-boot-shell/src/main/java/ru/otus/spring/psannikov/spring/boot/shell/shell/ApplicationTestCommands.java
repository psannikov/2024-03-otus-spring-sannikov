package ru.otus.spring.psannikov.spring.boot.shell.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.psannikov.spring.boot.shell.service.TestRunnerService;

@ShellComponent(value = "Application Test Commands")
@RequiredArgsConstructor
public class ApplicationTestCommands {
    TestRunnerService testRunnerService;

    @Autowired
    public ApplicationTestCommands(TestRunnerService testRunnerService) {
        this.testRunnerService = testRunnerService;
    }

    @ShellMethod(value = "Run test", key = {"r", "run"})
    public void runTest() {
        testRunnerService.run();
    }

}
