package ru.otus.spring.psannikov.react.rest.dto;

import ru.otus.spring.psannikov.react.domain.Person;

/**
 * DTO that represents Account
 */
@SuppressWarnings("all")
public class PersonDto {

    private int id = -1;
    private String name;

    public PersonDto() {
    }

    public PersonDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PersonDto toDto(Person person) {
        return new PersonDto(person.getId(), person.getName());
    }
}
