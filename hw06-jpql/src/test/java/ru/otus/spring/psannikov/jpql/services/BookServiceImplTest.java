package ru.otus.spring.psannikov.jpql.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.jpql.models.Author;
import ru.otus.spring.psannikov.jpql.models.Book;
import ru.otus.spring.psannikov.jpql.models.Genre;
import ru.otus.spring.psannikov.jpql.repositories.AuthorRepository;
import ru.otus.spring.psannikov.jpql.repositories.BookRepository;
import ru.otus.spring.psannikov.jpql.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

    private final static long ID = 1l;
    private Book mockBook;

    @BeforeEach
    public void setUp() {
        mockBook = new Book(ID, "Title_1",
                new Author(ID, "Author_1"),
                new Genre(ID, "Genre_1"));
    }


    @DisplayName("должен загружать книгу по ID")
    @Test
    public void findByIdTest() {
        when(bookRepository.findById(ID)).thenReturn(Optional.of(mockBook));
        var actualBook = bookService.findById(ID);
        assertThat(actualBook).isNotEmpty();
        assertThat(actualBook.get()).isEqualTo(mockBook);
    }
}
