package ru.otus.spring.psannikov.jpql.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.jpql.models.Author;
import ru.otus.spring.psannikov.jpql.models.Book;
import ru.otus.spring.psannikov.jpql.models.Genre;

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

    private static final long ID = 1l;
    private static final String TITLE = "Title_1";
    private static final String AUTHOR = "Author_1";
    private static final String GENRE = "Genre_1";
    private static final Book BOOK = new Book(ID, TITLE, new Author(ID, AUTHOR), new Genre(ID, GENRE));
    private static final String EXPECTED_AUTHOR_STRING = "Id: 1, title: Title_1, author: {Author_1}, genres: [Genre_1]";


    @DisplayName("должен вывести информацию о книге")
    @Test
    void bookToStringTest() {
        when(authorConverter.authorToString(any())).thenReturn(AUTHOR);
        when(genreConverter.genreToString(any())).thenReturn(GENRE);
        var actualBook = bookConverter.bookToString(BOOK);
        assertThat(actualBook).isEqualTo(EXPECTED_AUTHOR_STRING);
    }
}
