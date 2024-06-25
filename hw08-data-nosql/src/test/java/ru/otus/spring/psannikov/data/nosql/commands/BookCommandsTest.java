package ru.otus.spring.psannikov.data.nosql.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.psannikov.data.nosql.converters.BookConverter;
import ru.otus.spring.psannikov.data.nosql.models.Author;
import ru.otus.spring.psannikov.data.nosql.models.Book;
import ru.otus.spring.psannikov.data.nosql.models.Genre;
import ru.otus.spring.psannikov.data.nosql.services.BookService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Команды для Book должны")
@DataMongoTest
@Import(BookCommands.class)
public class BookCommandsTest {

    @Autowired
    private BookCommands bookCommands;

    @MockBean
    BookService bookService;

    @MockBean
    BookConverter bookConverter;

    private final static String ID_BOOK = "66732177350dbcd7b37d72e1";
    private final static String ID_AUTHOR = "6673218685210a129228297e";
    private final static String ID_GENRE = "6673218c4e7dccc973bcd9fa";
    private final static String TITLE = "Title_1";
    private final static String RETURN_STRING = "Тестовая книга";

    private Book mockBook;

//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @BeforeEach
    public void setUp() {
        mockBook = new Book(ID_BOOK, "Title_1",
                new Author(ID_AUTHOR, "Author_1"),
                new Genre(ID_GENRE, "Genre_1"),
                null);
    }

    @DisplayName("выводить информацию о всех книгах")
    @Test
    public void findAllBooksTest() {
        var mockBooks = List.of(mockBook);
        when(bookService.findAll()).thenReturn(mockBooks);
        when(bookConverter.bookToString(mockBook)).thenReturn(RETURN_STRING);
        String actualBooks = bookCommands.findAllBooks();
        assertThat(actualBooks).isEqualTo(RETURN_STRING);
//        assertEquals(RETURN_STRING, actualBooks);
    }

    @DisplayName("выводить информацию о книге по Id")
    @Test
    public void findBookByIdTest() {
        when(bookService.findById(ID_BOOK)).thenReturn(Optional.of(mockBook));
        when(bookConverter.bookToString(mockBook)).thenReturn(RETURN_STRING);
        var actualBook = bookCommands.findBookById(ID_BOOK);
        assertThat(actualBook).isEqualTo(RETURN_STRING);
    }

    @DisplayName("добавлять новую книгу")
    @Test
    public void insertBookTest() {
        when(bookService.insert(TITLE, ID_AUTHOR, ID_GENRE)).thenReturn(mockBook);
        when(bookConverter.bookToString(mockBook)).thenReturn(RETURN_STRING);
        var actualBook = bookCommands.insertBook(TITLE, ID_AUTHOR, ID_GENRE);
        assertThat(actualBook).isEqualTo(RETURN_STRING);
//        assertEquals(RETURN_STRING, actualBook);
    }

    @DisplayName("изменять существующую книгу")
    @Test
    public void updateBookTest() {
        when(bookService.update(ID_BOOK, TITLE, ID_AUTHOR, ID_GENRE)).thenReturn(mockBook);
        when(bookConverter.bookToString(mockBook)).thenReturn(RETURN_STRING);
        var actualBook = bookCommands.updateBook(ID_BOOK, TITLE, ID_AUTHOR, ID_GENRE);
        assertThat(actualBook).isEqualTo(RETURN_STRING);
//        assertEquals(RETURN_STRING, actualBook);
    }

    @DisplayName("должен удалять существующую книгу")
    @Test
    public void deleteBookTest() {
        bookCommands.deleteBook(ID_BOOK);
        verify(bookService, times(1)).deleteById(ID_BOOK);
    }
}
