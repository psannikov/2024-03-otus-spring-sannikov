package ru.otus.spring.psannikov.testconfigurationdemo.demo;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import ru.otus.spring.psannikov.testconfigurationdemo.family.FamilyMember;
import ru.otus.spring.psannikov.testconfigurationdemo.family.parents.Father;
import ru.otus.spring.psannikov.testconfigurationdemo.family.pets.Dog;

//@ComponentScan({"ru.otus.spring.psannikov.testconfigurationdemo.family.parents",
//        "ru.otus.spring.psannikov.testconfigurationdemo.family.childrens"})


@ComponentScan(value = "ru.otus.spring.psannikov.testconfigurationdemo.family",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Dog.class))

@SpringBootConfiguration
public class TestSpringBootConfiguration {
    @Bean
    FamilyMember father() {
        return new Father();
    }
}
