package ru.otus.spring.psannikov.testconfigurationdemo.family.parents;

import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.testconfigurationdemo.family.FamilyMember;

@Component
public class Mother extends FamilyMember {
    @Override
    public String getName() {
        return "Мама";
    }
}
