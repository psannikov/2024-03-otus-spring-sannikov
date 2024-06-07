package ru.otus.spring.psannikov.jpql.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.jpql.converters.BookConverter;
import ru.otus.spring.psannikov.jpql.models.Author;
import ru.otus.spring.psannikov.jpql.models.Book;
import ru.otus.spring.psannikov.jpql.models.Genre;
import ru.otus.spring.psannikov.jpql.services.BookService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Команды для Book должны")
@DataJpaTest
@Import(BookCommands.class)
public class BookCommandsTest {

    @Autowired
    private BookCommands bookCommands;

    @MockBean
    BookService bookService;

    @MockBean
    BookConverter bookConverter;

    private final static long ID =1l;
    private final static String RETURN_STRING = "Тестовая книга";

    @DisplayName("выводить информацию о книге по Id")
    @Test
    public void findBookByIdTest() {
        var mockBook = new Book(ID, "Title_1",
                new Author(ID, "Author_1"),
                new Genre(ID, "Genre_1"));
        when(bookService.findById(ID)).thenReturn(Optional.of(mockBook));
        when(bookConverter.bookToString(mockBook)).thenReturn(RETURN_STRING);
        var result = bookCommands.findBookById(ID);
        assertThat(result).isEqualTo(RETURN_STRING);

    }
}
