package ru.otus.spring.psannikov.testconfigurationdemo.family.childrens;

import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.testconfigurationdemo.family.FamilyMember;

@Component
public class Son extends FamilyMember {
    @Override
    public String getName() {
        return "Сын";
    }
}
