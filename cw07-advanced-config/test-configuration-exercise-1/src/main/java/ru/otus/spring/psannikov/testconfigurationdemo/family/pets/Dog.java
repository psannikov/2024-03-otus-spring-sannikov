package ru.otus.spring.psannikov.testconfigurationdemo.family.pets;

import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.testconfigurationdemo.family.FamilyMember;

@Component
public class Dog extends FamilyMember {

    @Override
    public String getName() {
        return "Собака";
    }
}
