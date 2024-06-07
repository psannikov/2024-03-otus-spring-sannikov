package ru.otus.spring.psannikov.jpql.commands;

import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private final static long ID = 1l;
    private final static String TITLE = "Title_1";
    private final static String RETURN_STRING = "Тестовая книга";

    private Book mockBook;

    @BeforeEach
    public void setUp() {
        mockBook = new Book(ID, "Title_1",
                new Author(ID, "Author_1"),
                new Genre(ID, "Genre_1"));
    }

    @DisplayName("выводить информацию о всех книгах")
    @Test
    public void findAllBooksTest() {
        var mockBooks = List.of(mockBook);
        when(bookService.findAll()).thenReturn(mockBooks);
        when(bookConverter.bookToString(mockBook)).thenReturn(RETURN_STRING);
        String actualBooks = bookCommands.findAllBooks();
        assertEquals(RETURN_STRING, actualBooks);
    }

    @DisplayName("выводить информацию о книге по Id")
    @Test
    public void findBookByIdTest() {
        when(bookService.findById(ID)).thenReturn(Optional.of(mockBook));
        when(bookConverter.bookToString(mockBook)).thenReturn(RETURN_STRING);
        var actualBook = bookCommands.findBookById(ID);
        assertThat(actualBook).isEqualTo(RETURN_STRING);
    }

    @DisplayName("добавлять новую книгу")
    @Test
    public void insertBookTest() {
        when(bookService.insert(TITLE, ID, ID)).thenReturn(mockBook);
        when(bookConverter.bookToString(mockBook)).thenReturn(RETURN_STRING);
        var actualBook = bookCommands.insertBook(TITLE, ID, ID);
        assertEquals(RETURN_STRING, actualBook);
    }

    @DisplayName("изменять существующую книгу")
    @Test
    public void updateBookTest() {
        when(bookService.update(ID, TITLE, ID, ID)).thenReturn(mockBook);
        when(bookConverter.bookToString(mockBook)).thenReturn(RETURN_STRING);
        var actualBook = bookCommands.updateBook(ID, TITLE, ID, ID);
        assertEquals(RETURN_STRING, actualBook);
    }
}
