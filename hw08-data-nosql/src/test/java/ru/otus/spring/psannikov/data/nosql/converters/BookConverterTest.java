package ru.otus.spring.psannikov.data.nosql.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.psannikov.data.nosql.models.Author;
import ru.otus.spring.psannikov.data.nosql.models.Book;
import ru.otus.spring.psannikov.data.nosql.models.Comment;
import ru.otus.spring.psannikov.data.nosql.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataMongoTest
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

    private final static String ID_BOOK = "66732177350dbcd7b37d72e1";
    private final static String ID_AUTHOR = "6673218685210a129228297e";
    private final static String ID_GENRE = "6673218c4e7dccc973bcd9fa";
    private final static String ID_COMMENT = "66732862546482e648b4265c";
    private static final String TITLE = "Title_1";
    private static final String AUTHOR = "Author_1";
    private static final String GENRE = "Genre_1";
    private static final String COMMENT = "Comment_1";
    private static final Book BOOK = new Book(ID_BOOK,
            TITLE,
            new Author(ID_AUTHOR, AUTHOR),
            new Genre(ID_GENRE, GENRE),
            List.of(new Comment(ID_COMMENT,COMMENT)));
    private static final String EXPECTED_BOOK_STRING = "\nId: %s, title: %s, \nauthor: {%s}, \ngenres: [%s], \ncomments: [%s]"
            .formatted(ID_BOOK, TITLE, AUTHOR, GENRE, COMMENT);

//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
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
