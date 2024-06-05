package ru.otus.spring.psannikov.data.nosql.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.data.nosql.models.Genre;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %s, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
