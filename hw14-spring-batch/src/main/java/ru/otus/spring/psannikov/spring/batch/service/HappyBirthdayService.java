package ru.otus.spring.psannikov.spring.batch.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.spring.batch.model.Person;

@Service
public class HappyBirthdayService {

    public Person doHappyBirthday(Person person){
        person.setAge(person.getAge() + 1);
        return person;
    }
}
