package ru.otus.spring.psannikov.webflux.domain;


import java.util.List;

public record PersonDto(String id, String name, Integer age, List<String> notes) {

}
