package ru.otus.spring.psannikov.jdbc.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.jdbc.models.Author;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
