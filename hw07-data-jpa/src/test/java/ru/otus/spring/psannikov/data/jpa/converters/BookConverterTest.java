package ru.otus.spring.psannikov.data.jpa.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.data.jpa.models.Author;
import ru.otus.spring.psannikov.data.jpa.models.Book;
import ru.otus.spring.psannikov.data.jpa.models.Comment;
import ru.otus.spring.psannikov.data.jpa.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@Import(BookConverter.class)
@DisplayName("Конвертер книг должен")
public class BookConverterTest {

    @Autowired
    private BookConverter bookConverter;

    @MockBean
    private AuthorConverter authorConverter;

    @MockBean
    private GenreConverter genreConverter;

    @MockBean
    private CommentConverter commentConverter;

    private static final long ID = 1L;
    private static final String TITLE = "Title_1";
    private static final String AUTHOR = "Author_1";
    private static final String GENRE = "Genre_1";
    private static final String COMMENT = "Comment_1";
    private static final Book BOOK = new Book(ID,
            TITLE,
            new Author(ID, AUTHOR),
            new Genre(ID, GENRE),
            List.of(new Comment(ID,COMMENT)));
    private static final String EXPECTED_BOOK_STRING = "Id: %d, title: %s, author: {%s}, genres: [%s], comments: [%s]"
            .formatted(ID, TITLE, AUTHOR, GENRE, COMMENT);


    @DisplayName("вывести информацию о книге")
    @Test
    void bookToStringTest() {
        when(authorConverter.authorToString(any())).thenReturn(AUTHOR);
        when(genreConverter.genreToString(any())).thenReturn(GENRE);
        when(commentConverter.commentToString(any())).thenReturn(COMMENT);
        var actualBook = bookConverter.bookToString(BOOK);
        assertThat(actualBook).isEqualTo(EXPECTED_BOOK_STRING);
    }
}
