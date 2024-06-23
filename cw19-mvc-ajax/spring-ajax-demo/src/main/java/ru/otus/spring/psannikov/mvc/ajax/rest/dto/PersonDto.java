package ru.otus.spring.psannikov.mvc.ajax.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.psannikov.mvc.ajax.domain.Person;

@Data
@AllArgsConstructor
public class PersonDto {

    private long id;
    private String name;

    public static PersonDto toDto(Person person) {
        return new PersonDto(person.getId(), person.getName());
    }

    public Person toDomainObject() {
        return new Person(id, name);
    }
}
