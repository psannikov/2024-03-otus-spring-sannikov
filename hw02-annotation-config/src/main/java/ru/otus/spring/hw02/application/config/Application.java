package ru.otus.spring.hw02.application.config;

import org.springframework.context.ApplicationContext;
import ru.otus.spring.hw02.application.config.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {

        //Создать контекст на основе Annotation/Java конфигурирования
        ApplicationContext context = null;
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}