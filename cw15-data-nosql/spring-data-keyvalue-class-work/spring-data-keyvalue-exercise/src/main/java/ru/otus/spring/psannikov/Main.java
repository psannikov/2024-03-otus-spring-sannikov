package ru.otus.spring.psannikov;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.psannikov.domain.Person;
import ru.otus.spring.psannikov.repostory.PersonRepository;

@SpringBootApplication
public class Main {

    @Autowired
    private PersonRepository repository;

    @PostConstruct
    public void init() {
        repository.save(new Person(1, "Pushkin"));

        repository.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
