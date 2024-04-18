package ru.otus.spring.psannikov.spring.boot;

import org.springframework.context.ApplicationContext;
import ru.otus.spring.psannikov.spring.boot.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {

        //Создать контекст Spring Boot приложения
        ApplicationContext context = null;
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}