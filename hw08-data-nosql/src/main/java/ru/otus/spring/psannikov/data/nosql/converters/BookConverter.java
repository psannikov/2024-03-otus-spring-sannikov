package ru.otus.spring.psannikov.data.nosql.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.data.nosql.models.Book;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookConverter {
    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    private final CommentConverter commentConverter;

    public String bookToString(Book book) {
        return "\nId: %s, title: %s, \nauthor: {%s}, \ngenres: [%s], \ncomments: [%s]".formatted(
                book.getId(),
                book.getTitle(),
                authorConverter.authorToString(book.getAuthor()),
                genreConverter.genreToString(book.getGenre()),
                book.getComments().isEmpty() ? null :
                        book.getComments()
                                .stream()
                                .map(commentConverter::commentToString)
                                .collect(Collectors.joining("," + System.lineSeparator())));
    }
}
