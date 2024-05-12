package ru.otus.spring.psannikov.jdbc.converters;

import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.jdbc.models.Genre;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
