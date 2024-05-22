package ru.otus.spring.psannikov.jpql.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.jpql.models.Genre;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
