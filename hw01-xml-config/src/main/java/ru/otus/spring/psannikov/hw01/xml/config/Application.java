package ru.otus.spring.psannikov.hw01.xml.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.psannikov.hw01.xml.config.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();
    }
}