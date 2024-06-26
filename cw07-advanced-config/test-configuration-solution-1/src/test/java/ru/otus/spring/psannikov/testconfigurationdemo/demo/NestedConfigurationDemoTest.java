package ru.otus.spring.psannikov.testconfigurationdemo.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.psannikov.testconfigurationdemo.family.FamilyMember;
import ru.otus.spring.psannikov.testconfigurationdemo.family.pets.Dog;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("В NestedConfigurationDemoTest семья должна ")
@SpringBootTest
//@SpringBootTest(classes = Dog.class)
@ContextConfiguration(classes = Dog.class)
public class NestedConfigurationDemoTest {

//    @ComponentScan("ru.otus.example.testconfigurationdemo.family.pets")
//    @Import(Dog.class)
    @Configuration
    static class NestedConfiguration {

//        @Bean
//        FamilyMember dog() {
//            return new Dog();
//        }

    }

    @Autowired
    private Map<String, FamilyMember> family;

    @DisplayName(" содержать только собаку ")
    @Test
    void shouldContainOnlyDog() {
        assertThat(family).containsOnlyKeys("dog");
    }

}
