package ru.otus.spring.psannikov.webflux.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ru.otus.spring.psannikov.webflux.models.Author;
import ru.otus.spring.psannikov.webflux.models.Book;
import ru.otus.spring.psannikov.webflux.models.Comment;
import ru.otus.spring.psannikov.webflux.models.Genre;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String id;

    private String title;

    private Author author;

    private Genre genre;

    private List<Comment> comments;

    public static BookDto entityToDto(Book book) {
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        return bookDto;
    }

    public static Book dtoToEntity(BookDto bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book);
        return book;
    }
}
