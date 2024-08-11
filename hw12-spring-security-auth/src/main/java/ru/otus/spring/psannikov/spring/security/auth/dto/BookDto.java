package ru.otus.spring.psannikov.spring.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.psannikov.spring.security.auth.models.Author;
import ru.otus.spring.psannikov.spring.security.auth.models.Book;
import ru.otus.spring.psannikov.spring.security.auth.models.Comment;
import ru.otus.spring.psannikov.spring.security.auth.models.Genre;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class BookDto {

    private long id;

    private String title;

    private long authorId;

    private String authorFullName;

    private long genreId;

    private String genreName;

    private List<Comment> comments;

    public static BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorId(book.getAuthor().getId())
                .authorFullName(book.getAuthor().getFullName())
                .genreId(book.getGenre().getId())
                .genreName(book.getGenre().getName())
                .comments(book.getComments())
                .build();
    }

    public Book toDomainObject() {
        return new Book(id, title, new Author(authorId, authorFullName), new Genre(genreId, genreName), comments);
    }

}
