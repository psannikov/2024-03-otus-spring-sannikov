package ru.otus.spring.hw02.application.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.hw02.application.config.service.TestRunnerService;

import java.util.logging.Logger;

@Configuration
@ComponentScan
public class Application {
    private static Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}