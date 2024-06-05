package ru.otus.spring.psannikov.data.nosql.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.data.nosql.models.Author;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %s, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
