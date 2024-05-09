package ru.otus.spring.psannikov.spring.boot.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.psannikov.spring.boot.shell.service.TestRunnerService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}