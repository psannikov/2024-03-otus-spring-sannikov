package ru.otus.spring.psannikov.spring.boot.commandlinerunners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.spring.boot.service.GreetingService;

import java.util.Arrays;


@Component
public class PreparationDev implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(PreparationDev.class);

    private final GreetingService greetingService;

    public PreparationDev(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void run(String... args) {
        //args парметры, котрые могут быть переданы в Main
        var applArgs = Arrays.toString(args);
        logger.info("DEV mode!!! Что-то настравиваем и подготавливаем, параметры: {} ", applArgs);

        var reply = greetingService.sayHello("Ivan");
        logger.info("reply:{}", reply);
    }

}
