package ru.otus.spring.psannikov.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Runner implements CommandLineRunner {
    private final ValueSource valueSource;

    public Runner(ValueSource valueSource) {
        this.valueSource = valueSource;
    }

    @Override
    public void run(String... args) {
        valueSource.generate();
    }
}
