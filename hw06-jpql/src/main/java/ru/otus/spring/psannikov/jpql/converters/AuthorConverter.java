package ru.otus.spring.psannikov.jpql.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.jpql.models.Author;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
