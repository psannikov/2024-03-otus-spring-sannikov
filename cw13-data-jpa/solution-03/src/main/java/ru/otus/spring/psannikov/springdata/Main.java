package ru.otus.spring.psannikov.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import ru.otus.spring.psannikov.springdata.domain.Email;
import ru.otus.spring.psannikov.springdata.domain.Person;
import ru.otus.spring.psannikov.springdata.repository.EmailRepository;
import ru.otus.spring.psannikov.springdata.repository.PersonRepository;

import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);

        PersonRepository personRepository = context.getBean(PersonRepository.class);
        EmailRepository emailRepository = context.getBean(EmailRepository.class);

        var pushkin = new Person("Александр Сергеевич Пушкин", new Email("alex.pushkin@mail.ru"));
        var lermontov = new Person("Михаил Юрьевич Лермонтов", new Email("michail.lermontov@mail.ru"));
        var gorbachev = new Person("Михаил Сергеевич Горбачев", new Email("gorbachev@mail.ru"));

        emailRepository.save(pushkin.getEmail());
        emailRepository.save(lermontov.getEmail());
        emailRepository.save(gorbachev.getEmail());

        personRepository.save(pushkin);
        personRepository.save(lermontov);
        personRepository.save(gorbachev);

        System.out.println("\n\nИщем всех пёрсонов");
        System.out.println(personRepository.findAll().stream().map(Objects::toString)
                .collect(Collectors.joining("\n")));

        System.out.println("\n\nИщем Пушкина");
        personRepository.findByName("Александр Сергеевич Пушкин")
                .ifPresent(System.out::println);

        System.out.println("\n\nИщем все почты");
        System.out.println(emailRepository.findAll().stream().map(Objects::toString)
                .collect(Collectors.joining("\n")));

        System.out.println("\n\nИщем Пушкина по его почте");
        personRepository.findByEmailAddress("alex.pushkin@mail.ru")
                .ifPresent(System.out::println);

        System.out.println("\n\n");
    }
}
