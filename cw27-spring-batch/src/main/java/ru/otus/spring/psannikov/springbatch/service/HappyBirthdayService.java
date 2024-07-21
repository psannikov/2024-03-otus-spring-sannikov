package ru.otus.spring.psannikov.springbatch.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.springbatch.model.Person;

@Service
public class HappyBirthdayService {

    public Person doHappyBirthday(Person person){
        person.setAge(person.getAge() + 1);
        return person;
    }
}
