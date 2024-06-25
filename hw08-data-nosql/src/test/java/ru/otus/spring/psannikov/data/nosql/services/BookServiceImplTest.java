package ru.otus.spring.psannikov.data.nosql.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.data.nosql.models.Author;
import ru.otus.spring.psannikov.data.nosql.models.Book;
import ru.otus.spring.psannikov.data.nosql.models.Genre;
import ru.otus.spring.psannikov.data.nosql.repositories.AuthorRepository;
import ru.otus.spring.psannikov.data.nosql.repositories.BookRepository;
import ru.otus.spring.psannikov.data.nosql.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Сервис для Book должен")
@DataMongoTest
@Import(BookServiceImpl.class)
public class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private BookRepository bookRepository;

    private final static String ID_BOOK = "66732177350dbcd7b37d72e1";
    private final static String ID_AUTHOR = "6673218685210a129228297e";
    private final static String ID_GENRE = "6673218c4e7dccc973bcd9fa";
    private final static String ID_COMMENT = "66732862546482e648b4265c";
    private final static String TITLE = "Title_1";
    private final static String AUTHOR = "Author_1";
    private final static String GENRE = "Genre_1";
    private Book mockBook;
    private Author mockAuthor;
    private Genre mockGenre;

    @BeforeEach
    public void setUp() {
        mockAuthor = new Author(ID_AUTHOR, AUTHOR);
        mockGenre = new Genre(ID_GENRE, GENRE);
        mockBook = new Book(ID_BOOK, TITLE, mockAuthor, mockGenre, null);
    }


    @DisplayName("должен загружать книгу по ID")
    @Test
    public void findByIdTest() {
        when(bookRepository.findById(ID_BOOK)).thenReturn(Optional.of(mockBook));
        var actualBook = bookService.findById(ID_BOOK);
        assertThat(actualBook).isNotEmpty();
        assertThat(actualBook.get()).isEqualTo(mockBook);
    }

    @DisplayName("должен загружать все книги")
    @Test
    public void findAllTest() {
        var booksMock = List.of(mockBook);
        when(bookRepository.findAll()).thenReturn(booksMock);
        var authors = bookService.findAll();
        assertThat(authors).isEqualTo(booksMock);
    }

    @DisplayName("должен добавлять новую книгу")
    @Test
    public void insertTest() {
        when(authorRepository.findById(ID_AUTHOR)).thenReturn(Optional.of(mockAuthor));
        when(genreRepository.findById(ID_GENRE)).thenReturn(Optional.of(mockGenre));
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(mockBook);
        var actualBook = bookService.insert(TITLE, ID_AUTHOR, ID_GENRE);
        assertThat(actualBook).isEqualTo(mockBook);
        verify(authorRepository, times(1)).findById(ID_AUTHOR);
        verify(genreRepository, times(1)).findById(ID_GENRE);
        verify(bookRepository, times(1)).save(Mockito.any(Book.class));
    }

    @DisplayName("должен изменять существующую книгу")
    @Test
    public void updateTest() {
        when(authorRepository.findById(ID_AUTHOR)).thenReturn(Optional.of(mockAuthor));
        when(genreRepository.findById(ID_GENRE)).thenReturn(Optional.of(mockGenre));
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(mockBook);
        var actualBook = bookService.update(ID_BOOK, TITLE, ID_AUTHOR, ID_GENRE);
        assertThat(actualBook).isEqualTo(mockBook);
        verify(authorRepository, times(1)).findById(ID_AUTHOR);
        verify(genreRepository, times(1)).findById(ID_GENRE);
        verify(bookRepository, times(1)).save(Mockito.any(Book.class));
    }

    @DisplayName("должен удалять существующую книгу")
    @Test
    public void deleteByIdTest() {
        bookService.deleteById(ID_BOOK);
        verify(bookRepository, times(1)).deleteById(ID_BOOK);
    }

}
