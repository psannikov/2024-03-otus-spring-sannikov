package ru.otus.spring.psannikov.docker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.docker.models.Author;
import ru.otus.spring.psannikov.docker.models.Book;
import ru.otus.spring.psannikov.docker.models.Genre;
import ru.otus.spring.psannikov.docker.repositories.AuthorRepository;
import ru.otus.spring.psannikov.docker.repositories.BookRepository;
import ru.otus.spring.psannikov.docker.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Сервис для Book должен")
@DataJpaTest
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

    private final static long ID = 1L;
    private final static String TITLE = "Title_1";
    private final static String AUTHOR = "Author_1";
    private final static String GENRE = "Genre_1";
    private Book mockBook;
    private Author mockAuthor;
    private Genre mockGenre;

    @BeforeEach
    public void setUp() {
        mockAuthor = new Author(ID, AUTHOR);
        mockGenre = new Genre(ID, GENRE);
        mockBook = new Book(ID, TITLE, mockAuthor, mockGenre, null);
    }


    @DisplayName("должен загружать книгу по ID")
    @Test
    public void findByIdTest() {
        when(bookRepository.findById(ID)).thenReturn(Optional.of(mockBook));
        var actualBook = bookService.findById(ID);
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
        when(authorRepository.findById(ID)).thenReturn(Optional.of(mockAuthor));
        when(genreRepository.findById(ID)).thenReturn(Optional.of(mockGenre));
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(mockBook);
        var book = bookService.insert(TITLE, ID, ID);
        assertEquals(mockBook, book);
        verify(authorRepository, times(1)).findById(ID);
        verify(genreRepository, times(1)).findById(ID);
        verify(bookRepository, times(1)).save(Mockito.any(Book.class));
    }

    @DisplayName("должен изменять существующую книгу")
    @Test
    public void updateTest() {
        when(authorRepository.findById(ID)).thenReturn(Optional.of(mockAuthor));
        when(genreRepository.findById(ID)).thenReturn(Optional.of(mockGenre));
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(mockBook);
        var book = bookService.update(ID, TITLE, ID, ID, new ArrayList<>());
        assertEquals(mockBook, book);
        verify(authorRepository, times(1)).findById(ID);
        verify(genreRepository, times(1)).findById(ID);
        verify(bookRepository, times(1)).save(Mockito.any(Book.class));
    }

    @DisplayName("должен удалять существующую книгу")
    @Test
    public void deleteByIdTest() {
        bookService.deleteById(ID);
        verify(bookRepository, times(1)).deleteById(ID);
    }

}
